# Kubernets Engine APIが有効であることを確認
# https://console.cloud.google.com/apis/library/container.googleapis.com?q=kubernetes+engine&hl=ja&_ga=2.238662912.-1473604364.1575704135
# プロジェクトIDを設定
gcloud config set project [YOUR_PROJECT_ID]

# VPCネットワーク作成
gcloud compute networks create gke-network --subnet-mode custom
# サブネット作成
gcloud compute networks subnets create easy-mvc-network \
    --network gke-network \
    --region=us-west1 \
    --range 192.168.0.0/20
# ファイアウォール設定
gcloud compute firewall-rules create gke-network-rule01 --network gke-network --allow tcp:80,icmp

# Kubernetesクラスターを作成
gcloud container clusters create easy-mvc-gke \
    --enable-ip-alias \
    --cluster-ipv4-cidr=/16 \
    --services-ipv4-cidr=/22 \
    --zone=us-west1-a \
    --network=gke-network \
    --subnetwork=easy-mvc-network \

# Service Networking APIが有効であることを確認
# VPCネットワーク内でIPアドレス範囲を割り当てる
gcloud compute addresses create sql-allocated-range \
    --global \
    --purpose=VPC_PEERING \
    --prefix-length=16 \
    --description="allocated for Cloud SQL" \
    --network=gke-network

# プライベート接続の作成
gcloud services vpc-peerings connect \
    --service=servicenetworking.googleapis.com \
    --ranges=sql-allocated-range \
    --network=gke-network \
    --project=[YOUR_PROJECT_ID]

# CloudSQL MySQLインスタンス(プライベートIPアドレス割り当て)の作成
gcloud beta sql instances create mysql-service-gke \
  --tier=db-n1-standard-2 \
  --database-version=MYSQL_5_7 \
  --region=us-west1 \
  --network=gke-network \
  --no-assign-ip
# インスタンスのアプリで利用するユーザーの生成
gcloud sql users create springboot \
  --instance=mysql-service-gke \
  --password=p@ssw0rd
# インスタンス上でeasy_mvc_dbという名前のデータベースを作成
gcloud sql databases create easy_mvc_db \
  --instance=mysql-service-gke



# 環境変数の定義
# Cloud SQLのプライベートIPアドレス
export CLOUDSQL_MYSQL_HOST=$(gcloud sql instances describe mysql-service-gke --format="value(ipAddresses[].ipAddress)")
# 現在のプロジェクトID(=YOUR_PROJECT_ID)
export PROJECT_ID=$(gcloud config get-value project)

kubectl create configmap app-config --from-literal=db.host=${CLOUDSQL_MYSQL_HOST}

# Dockerfileをビルドしてコンテナレジストリへイメージをプッシュする
gcloud builds submit easy-mvc-gke/vuejs-app-gke --tag gcr.io/${PROJECT_ID}/vuejs-app-gke
gcloud builds submit easy-mvc-gke/springboot-app-gke --tag gcr.io/${PROJECT_ID}/springboot-app-gke

# リソースファイルをkubectlで適用(project-all-in-oneはmanifestsディレクトリにあるyamlをまとめたもの)
kubectl apply -f easy-mvc-gke/project-all-in-one.yml
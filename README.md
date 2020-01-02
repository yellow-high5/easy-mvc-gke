# Easy MVC Application for Google Kubernetes Engine

## What?

This Project is Easy Simple Application(Vue.js + Spring Boot + MySQL) on GKE.  
Kubernetes is container orchestration tool. GKE is a managed service that abstracts kubernetes so that even beginners of infrastructure can easily operate.  
It helps you understand the approach of building a basic application consisting of a web server, application server, and database in a container and deploying it to a GKE environment.

## How to hands on

### Local Development

You have to install Docker and Docker Compose on your local computer.  
cf. My Local Computer(MacBook Pro): Docker version 19.03.2, docker-compose version 1.24.1  
Maybe, it takes a long time to `mvn install` in build stage of Dockerfile.

```sh
$ git clone https://github.com/yellow-high5/easy-mvc-gke.git
$ cd easy-mvc-gke
$ docker-compose build
$ docker-compose up
```

### Google Kubernetes Engine

You need Google Cloud Platform Account.  
👉You need checking Kubernets Engine API and Service Networking API is enabled  
cf. My GKE Version: 1.13.11-gke.14

```sh
# On your Google Cloud Shell
$ gcloud config set project [YOUR_PROJECT_ID]
$ git clone https://github.com/yellow-high5/easy-mvc-gke.git
$ cd easy-mvc-gke
$ chmod 700 ./production-gke-exe
$ sh production-gke-exe $(gcloud config get-value project)
```

### If it works correctly...

If you can access localhost or public ip on GKE Service, you should see the following screen.

<img width="1043" alt="screenshot-interface" src="https://user-images.githubusercontent.com/14067398/71486087-ece64080-2857-11ea-89fc-f53cbf678961.png">

"No data available" maybe signs you have no data or you cannot connect in your CloudSQL MySQL DB correctly.

## Thanks for the hint

- [https://cloud.google.com/kubernetes-engine/docs](https://cloud.google.com/kubernetes-engine/docs)
- [https://github.com/eficode/wait-for](https://github.com/eficode/wait-for)

## Licence

[MIT](https://opensource.org/licenses/MIT)

## Author

[yoshinori matsuzaki](https://github.com/yellow-high5)

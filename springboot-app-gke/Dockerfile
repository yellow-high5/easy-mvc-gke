# ビルド環境
FROM maven:3.6-jdk-8 as build-stage
WORKDIR /app
COPY src /app/src
COPY pom.xml /app
RUN mvn install 

# 本番環境
FROM openjdk:8-jdk-alpine
COPY --from=build-stage /app/target/*.jar app.jar
COPY wait-for ./
RUN chmod 700 ./wait-for
ENTRYPOINT ["java","-Dspring.profiles.active=gke","-jar","app.jar"]

# mvnがインストールされているならmvn install実行後に以下でDockerビルドした方が早い
# FROM openjdk:8-jdk-alpine
# VOLUME /tmp
# ARG JAR_FILE=target/*.jar
# ARG DEPLOY_ENV="gke"
# ENV APP_PROFILE=${DEPLOY_ENV}
# COPY ${JAR_FILE} app.jar
# ENTRYPOINT ["java","-Dspring.profiles.active=${APP_PROFILE}","-jar","app.jar"]
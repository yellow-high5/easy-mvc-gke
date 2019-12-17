FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","app.jar"]

# ビルド環境
# FROM maven:3.6-jdk-8 as build-stage
# WORKDIR /app
# COPY src ./
# COPY pom.xml ./
# RUN mvn -f /app/pom.xml clean package #ビルドエラー発生

# 本番環境
# FROM openjdk:8-jdk-alpine
# VOLUME /tmp
# COPY --from=build-stage /app/target/*.jar app.jar
# ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","app.jar"]
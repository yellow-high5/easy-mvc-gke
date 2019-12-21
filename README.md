Easy MVC Application for Google Kubernetes Engine
====

<img width="419" alt="overview" src="https://user-images.githubusercontent.com/14067398/52426649-adc8d700-2b41-11e9-9c26-59ee7671d1d9.png">

## What?
This Project is Easy Simple Application(Vue.js + Spring Boot + MySQL) on GKE.  
Kubernetes is container orchestration tool. GKE is a managed service that abstracts kubernetes so that even beginners of infrastructure can easily operate.  
It helps you understand the approach of building a basic application consisting of a web server, application server, and database in a container and deploying it to a GKE environment.  

## How to hands on

### Local Development
You have to install Docker and Docker Compose on your local computer.  
👉You need checking Kubernets Engine API and Service Networking API is enabled  
cf. My Local Computer(MacBook Pro): Docker version 19.03.2, docker-compose version 1.24.1  
Maybe, it takes a long time to `mvn install` in build stage of Dockerfile.  

```sh
$ git clone [THIS_REPOSITORY_GIT_URL]
$ docker-compose build
$ docker-compose up -d
```

### Google Kubernetes Engine
You need Google Cloud Platform Account.  
cf. My GKE Version: 1.13.11-gke.14  

```sh
# On your Google Cloud Shell
$ git clone [THIS_REPOSITORY_GIT_URL]
$ sh production-gke-exe.sh
$ kubectl create -f project-all-in-one.yml
```

### If it works correctly...
If you can access localhost or public ip on GKE Service, you should see the following screen.  

![screenshot](https://user-images.githubusercontent.com/14067398/71311144-3dc4f600-2460-11ea-8f21-cd3cf736a6d0.png)

FROM openjdk:21
MAINTAINER lemon.org
COPY target/docker-basic-0.0.1-SNAPSHOT.jar docker-basic-0.0.1.jar
ENTRYPOINT ["java","-jar","/docker-basic-0.0.1.jar"]

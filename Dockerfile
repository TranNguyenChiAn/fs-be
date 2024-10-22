FROM maven:3.8.5-openjdk-17 AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

RUN ./mvnw package

FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=target /target/demo-1.jar demo.jar

ENTRYPOINT ["java", "-jar", "demo.jar"]

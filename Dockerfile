FROM maven:3.8.5-openjdk-17 AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

RUN ./mvnw package

FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=target /target/demo1.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

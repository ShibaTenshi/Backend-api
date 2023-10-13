FROM openjdk:17-jdk-slim
COPY target/Backend-api-0.0.1.jar api.jar
EXPOSE 5041
ENTRYPOINT ["java","-jar","./api.jar"]
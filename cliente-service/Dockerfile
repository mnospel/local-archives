FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ./target/cliente-service-0.0.1.jar cliente-service.jar
CMD ["java", "-jar", "cliente-service.jar"]

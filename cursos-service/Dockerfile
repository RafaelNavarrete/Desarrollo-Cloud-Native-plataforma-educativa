FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY target/*.jar app.jar
COPY src/main/resources/wallet /app/wallet
ENV TNS_ADMIN=/app/wallet
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]
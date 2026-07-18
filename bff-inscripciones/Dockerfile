FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY target/bff-inscripciones-0.0.1-SNAPSHOT.jar app.jar
RUN mkdir -p /app/temp
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]

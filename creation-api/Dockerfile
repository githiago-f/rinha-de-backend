FROM eclipse-temurin:17.0.9_9-jre-alpine

WORKDIR /app
COPY target/creation-api-*.*.*.jar /app/api.jar

CMD ["java", "-jar", "/app/api.jar"]

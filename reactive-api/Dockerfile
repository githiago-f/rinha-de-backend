FROM eclipse-temurin:17.0.9_9-jre-alpine

WORKDIR /app
COPY target/reactive-api-*.*.*.jar /app/api.jar

CMD ["java", "-jar", "/app/api.jar"]

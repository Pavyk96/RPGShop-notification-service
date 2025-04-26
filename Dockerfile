FROM openjdk:21-jdk-slim

WORKDIR /app

COPY build/libs/notification-0.0.1-SNAPSHOT.jar /app/notification.jar

ENTRYPOINT ["java", "-jar", "/app/notification.jar"]
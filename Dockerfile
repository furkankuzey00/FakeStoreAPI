# Steg 1: Använd en OpenJDK-bild istället för Ubuntu
FROM openjdk:21-jdk-slim

# Steg 2: Ange författaren av denna Dockerfile
LABEL authors="furka"

# Steg 3: Skapa en arbetskatalog i containern
WORKDIR /app

# Steg 4: Kopiera din JAR-fil från din byggmapp till containern
COPY build/libs/StoreAPI-0.0.1-SNAPSHOT.jar /app/app.jar

# Steg 5: Exponera port 8080 (standardport för Spring Boot)
EXPOSE 8080

# Steg 6: Starta applikationen med Java
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

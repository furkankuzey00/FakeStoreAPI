# Steg 1: Använd en OpenJDK-bild för att bygga applikationen
FROM openjdk:21-jdk-slim AS build

# Steg 2: Installera nyare version av Gradle (t.ex. Gradle 8.8)
RUN apt-get update && apt-get install -y wget unzip && \
    wget https://services.gradle.org/distributions/gradle-8.8-bin.zip && \
    unzip gradle-8.8-bin.zip -d /opt && \
    ln -s /opt/gradle-8.8/bin/gradle /usr/bin/gradle

# Steg 3: Kopiera källkod
WORKDIR /app
COPY . .

# Steg 4: Bygg applikationen med Gradle och skriv ut eventuella rapporter vid fel
RUN gradle build --no-daemon --stacktrace || (cat build/reports/*.txt && exit 1)

# Steg 5: Använd en ren OpenJDK-bild för att köra applikationen
FROM openjdk:21-jdk-slim

# Steg 6: Ange författare av denna Dockerfile
LABEL authors="furka"

# Steg 7: Skapa en arbetskatalog i containern
WORKDIR /app

# Steg 8: Kopiera din JAR-fil från byggmappen till containern
COPY --from=build /app/build/libs/*.jar /app/app.jar

# Steg 9: Exponera port 8080 (standardport för Spring Boot)
EXPOSE 8080

# Steg 10: Starta applikationen med Java
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

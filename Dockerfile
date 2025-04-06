# Steg 1: Använd en basbild (ubuntu:latest)
FROM ubuntu:latest

# Steg 2: Ange författaren av denna Dockerfile
LABEL authors="furka"

# Steg 3: Installera nödvändiga beroenden för att köra Java (t.ex., OpenJDK)
RUN apt-get update && apt-get install -y openjdk-21-jdk

# Steg 4: Skapa en arbetskatalog i containern
WORKDIR /app

# Steg 5: Kopiera ditt projekt (antingen .jar eller andra filer) till containern
COPY build/libs/your-app.jar /app/your-app.jar

# Steg 6: Exponera den port som din applikation kommer att använda (8080 är standard för Spring Boot)
EXPOSE 8080

# Steg 7: Lägg till ENTRYPOINT för att köra `top`-kommandot i bakgrundsläge
ENTRYPOINT ["top", "-b"]

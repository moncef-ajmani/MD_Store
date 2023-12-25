# Use an official Maven image as a build stage
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the application source code
COPY src ./src
RUN mvn package

# Build the final image
FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/*.jar auth-service.jar
EXPOSE 8081
CMD ["java", "-jar", "auth-service.jar"]
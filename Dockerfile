# Use OpenJDK as base image
FROM openjdk:17-jdk-slim

# Add metadata
LABEL maintainer="your-email@example.com"

# Create app directory
WORKDIR /app

# Copy target jar file into the container
COPY target/fileSharingManagement-0.0.1-SNAPSHOT.jar app.jar

# Expose the port Spring Boot runs on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]

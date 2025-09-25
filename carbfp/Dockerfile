# Use OpenJDK 21 as base image
FROM openjdk:21-jdk-slim

# Set working directory in container
WORKDIR /app

# Copy Maven wrapper and pom.xml for dependency caching
COPY carbfp/pom.xml .
COPY carbfp/mvnw .
COPY carbfp/.mvn .mvn

# Make mvnw executable
RUN chmod +x mvnw

# Copy source code
COPY carbfp/src ./src

# Build the application
RUN ./mvnw clean package -DskipTests

# Expose the port your app runs on
EXPOSE 8080

# Run the Spring Boot JAR
CMD ["java", "-jar", "target/carbfp-0.0.1-SNAPSHOT.jar"]

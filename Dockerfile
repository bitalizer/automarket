# Use a small base image
FROM eclipse-temurin:17-jre-alpine

# Create and set a working directory
WORKDIR /app

# Set a non-root user
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

# Copy the Spring Boot application JAR file into the container
COPY build/libs/*.jar app.jar

# Expose the port your Spring Boot app runs on (adjust as needed)
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]

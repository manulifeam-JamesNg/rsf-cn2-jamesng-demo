FROM artifactory.ap.manulife.com/docker/rsf/rsf-java-newrelic:1.0.2

# Set working directory
WORKDIR /app

# Copy the JAR file
COPY target/rsf-connect-mongodb-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

FROM artifactory.ap.manulife.com/docker/rsf/rsf-java-newrelic:1.0.2
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

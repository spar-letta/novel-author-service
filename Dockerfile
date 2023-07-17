FROM openjdk:11

EXPOSE 8083

COPY target/*.jar /

ENTRYPOINT ["java", "-jar", "author-service-0.0.1-SNAPSHOT.jar"]
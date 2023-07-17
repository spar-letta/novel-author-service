FROM openjdk:11

EXPOSE 8083

ADD target/author-service-0.0.1-SNAPSHOT.jar author-service-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "author-service-0.0.1-SNAPSHOT.jar"]
FROM openjdk:21-jdk-slim

LABEL "org.opencontainers.image.authors"="sandydocker14.com"

COPY target/account-0.0.1-SNAPSHOT.jar account-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","account-0.0.1-SNAPSHOT.jar"]
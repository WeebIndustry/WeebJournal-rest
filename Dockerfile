FROM openjdk:8-jdk-alpine

LABEL maintainer="harrycoder1408@gmail.com"

VOLUME /tmp

EXPOSE 1408

ARG JAR_FILE=build/libs/weebjournal-0.0.1-SNAPSHOT.jar

ARG PROPERTIES=application.properties

ADD ${JAR_FILE} weebjournal.jar

ADD ${PROPERTIES} application.properties

ENTRYPOINT [ "java", "-Dspring.config.location=application.properties", "-jar", "/weebjournal.jar"]
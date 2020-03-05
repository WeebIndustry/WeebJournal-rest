# openjdk is good enough to run
FROM openjdk:8-jdk-alpine

ARG JAR_FILE=build/libs/weebjournal-0.0.1-SNAPSHOT.jar

VOLUME /tmp

EXPOSE 1408

ARG DB_URL

ARG DB_UNAME

ARG DB_PWD

ENV DB_URL=$DB_URL

ENV DB_UNAME=$DB_UNAME

ENV DB_PWD=$DB_PWD

ADD ${JAR_FILE} weebjournal.jar


ENTRYPOINT [ "java", "-jar", "weebjournal.jar", "--spring.jpa.database=POSTGRESQL", "--spring.datasource.platform=postgres", "--spring.datasource.url=$DB_URL", "--spring.datasource.username=$DB_UNAME", "--spring.jpa.show-sql=true", "--spring.jpa.generate-dll=true", "--spring.jpa.hibernate.dll-auto=update", "--spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true"]
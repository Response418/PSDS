FROM openjdk:17-slim

#RUN apt-get update && apt-get install telnet && apt-get install traceroute

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} psds.jar

ENV TZ=Europe/Moscow

EXPOSE 8080

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/psds.jar"]
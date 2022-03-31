FROM openjdk:14-jdk-alpine
ARG JAR_FILE=build/libs/*SNAPSHOT-all.jar
COPY ${JAR_FILE} customerDbService.jar
ENTRYPOINT ["java","-jar","customerDbService.jar"]
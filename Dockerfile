#
# Package stage
#
FROM openjdk:11-jre-slim
EXPOSE 8080

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java","-jar","application.jar"]

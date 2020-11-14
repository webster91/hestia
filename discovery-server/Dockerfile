FROM maven:3.6.3-jdk-11
EXPOSE 5101 5102

COPY /target/discovery-server.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
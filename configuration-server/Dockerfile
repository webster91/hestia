FROM maven:3.6.3-jdk-11
EXPOSE 5000
COPY /target/configuration-server.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
FROM maven:3.6.3-jdk-11
EXPOSE 7110
COPY /target/hestia.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
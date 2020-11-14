FROM maven:3.6.3-jdk-11
EXPOSE 5200

COPY /target/gateway-server.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

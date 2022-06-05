FROM openjdk:17-oracle
COPY target/chat-api-0.0.1-SNAPSHOT.jar /chat-api-0.0.1.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "/chat-api-0.0.1.jar"]
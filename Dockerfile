FROM maven:3.8.5-openjdk-18 AS build
COPY src /src
COPY pom.xml /
RUN --mount=type=cache,target=/root/.m2 mvn -f /pom.xml clean package

FROM openjdk:18-oracle
COPY --from=build target/chat-api-0.0.1-SNAPSHOT.jar /chat-api-0.0.1.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "/chat-api-0.0.1.jar"]
#FROM openjdk:21
#WORKDIR /app
#COPY /target/demo-0.0.1-SNAPSHOT.jar demo-0.0.1-SNAPSHOT.jar
#CMD ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]

FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline -B

COPY src ./src
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

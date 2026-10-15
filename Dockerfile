FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
COPY .mvn/ .mvn/
COPY mvnw .
RUN chmod +x mvnw

COPY src/ src/
RUN ./mvnw -B clean package

FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=build /app/target/classes /app/classes

CMD ["java", "-cp", "/app/classes", "application.App"]

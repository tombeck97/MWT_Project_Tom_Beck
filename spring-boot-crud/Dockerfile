FROM openjdk:17

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

COPY src src

EXPOSE 8080

CMD ["./mvnw", "spring-boot:run"]
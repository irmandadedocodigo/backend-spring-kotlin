FROM maven:3.9-amazoncorretto-21 as builder

WORKDIR /builder

COPY . .

RUN mvn clean package -D spring-boot.run.profiles=prod -D skipTests --batch-mode

FROM amazoncorretto:21-alpine

COPY --from=builder /builder/target/*.jar /app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
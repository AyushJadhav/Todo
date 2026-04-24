# Use modern Eclipse Temurin JDK (recommended replacement for OpenJDK images)
FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

RUN chmod +x mvnw

RUN ./mvnw clean package -DskipTests

CMD ["java", "-jar", "target/todo-app-backend-0.0.1-SNAPSHOT.jar"]
FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

RUN chmod +x ./gradlew
RUN ./gradlew clean build -x test

EXPOSE 8080

CMD ["sh", "-c", "java -jar build/libs/todo-app-0.0.1-SNAPSHOT.jar"]
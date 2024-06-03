FROM maven:3.8.3-openjdk-17@sha256:8a66581a077762c8752a9f64f73cdd8c59e9c4446eb810417119e0436b075931 AS MAVEN_BUILD
WORKDIR /interop-be-probing-response-updater/
COPY . .
RUN mvn -q clean package -Dmaven.test.skip=true

FROM eclipse-temurin:17-jre-alpine@sha256:ad9223070abcf5716e98296a98c371368810deb36197b75f3a7b74815185c5e3
WORKDIR /app
COPY --from=MAVEN_BUILD /interop-be-probing-response-updater/target/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
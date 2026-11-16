# ----------- Stage 1: Build the app ----------- #
FROM gradle:8.14.3-jdk17 AS builder

WORKDIR /app

COPY --chown=gradle:gradle . .

RUN gradle bootJar --no-daemon

# ----------- Stage 2: Run the app ----------- #
FROM eclipse-temurin:17-jre-jammy

LABEL org.opencontainers.image.source=https://github.com/generiekzaakafhandelcomponent/bag-api-simulator

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 5020

ENTRYPOINT ["java", "-jar", "app.jar"]

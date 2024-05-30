FROM openjdk:17-slim-bullseye
VOLUME /tmp
COPY build/libs/Backend-0.0.1-SNAPSHOT.jar /app/app.jar
RUN useradd -m spring && chown -R spring:spring /app
USER spring
EXPOSE 8080
HEALTHCHECK CMD curl --fail http://localhost:8080/actuator/health || exit 1
CMD ["java", "-jar", "/app/app.jar"]

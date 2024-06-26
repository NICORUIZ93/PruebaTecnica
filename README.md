# PruebaTecnica

Para correr el proyecto utilizando Docker, sigue estos pasos:

Clona el repositorio del proyecto en tu máquina local.

```
https://github.com/NICORUIZ93/PruebaTecnica.git
```

Crea un archivo Dockerfile: Crea un archivo llamado Dockerfile en la raíz del proyecto.
Escribe el Dockerfile:
dockerfile

```
FROM openjdk:17-slim-bullseye
VOLUME /tmp
COPY build/libs/Backend-0.0.1-SNAPSHOT.jar /app/app.jar
RUN useradd -m spring && chown -R spring:spring /app
USER spring
EXPOSE 8080
HEALTHCHECK CMD curl --fail http://localhost:8080/actuator/health || exit 1
CMD ["java", "-jar", "/app/app.jar"]
```

Crea la imagen: Ejecuta el comando 

```docker build -t nombre-imagen . ```

```docker build -t backend-app . ```

para crear la imagen Docker.
Ejecuta la imagen: Ejecuta el comando 

```docker run -p 8080:8080 backend-app``` 

para ejecutar la imagen y hacer que el proyecto esté disponible en el puerto 8080.

Nota: Antes de crear la imagen, asegúrate de ejecutar el comando 

```gradle build```

para construir el proyecto.
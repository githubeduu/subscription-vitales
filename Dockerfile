FROM openjdk:21-ea-24-oracle

WORKDIR /app

COPY target/subscription-vitales-0.0.1-SNAPSHOT.jar app.jar

# Crea el directorio donde se almacenarán los archivos
RUN mkdir -p /app/informes

# Dar permisos de lectura, escritura y ejecución al directorio
RUN chmod -R 777 /app/informes


EXPOSE 8082

CMD ["java", "-jar", "app.jar"]

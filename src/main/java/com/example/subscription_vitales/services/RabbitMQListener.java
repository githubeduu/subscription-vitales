package com.example.subscription_vitales.services;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RabbitMQListener {


    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "señales_vitales")
    public void recibirMensaje(String mensajeJson) {
        try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmm");
        String fechaHora = LocalDateTime.now().format(formatter);

        String nombreArchivo = "resumen_senales_vitales_" + fechaHora + ".json";

        //localhost
        //File archivo = new File("src/main/java/com/example/subscription_vitales/informes/" + nombreArchivo);
        //Docker
        File archivo = new File( "/app/informes/"  + nombreArchivo);
       
        objectMapper.writeValue(archivo, mensajeJson);

        System.out.println("señales_vitales guardada correctamente");

        } catch (Exception e) {
            System.err.println("Error al procesar el mensaje: " + e.getMessage());
        }
    }
}
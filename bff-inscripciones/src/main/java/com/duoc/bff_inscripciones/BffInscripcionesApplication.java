package com.duoc.bff_inscripciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal del microservicio BFF de inscripciones.
 * Este servicio orquesta la generacion del resumen de inscripcion,
 * su almacenamiento en AWS S3 y la publicacion de mensajes en RabbitMQ
 * hacia el microservicio cursos-service.
 *
 * @author Rafael Navarrete
 */
@SpringBootApplication
public class BffInscripcionesApplication {

    public static void main(String[] args) {
        SpringApplication.run(BffInscripcionesApplication.class, args);
    }

}

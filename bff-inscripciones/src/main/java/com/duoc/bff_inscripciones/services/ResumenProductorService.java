package com.duoc.bff_inscripciones.services;

import com.duoc.bff_inscripciones.config.RabbitMQConfig;
import com.duoc.bff_inscripciones.model.ResumenInscripcion;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * Servicio productor que envia el mensaje del resumen de inscripcion
 * a la cola principal de RabbitMQ para que sea consumido por cursos-service.
 *
 * @author Rafael Navarrete
 */
@Service
public class ResumenProductorService {

    private final RabbitTemplate rabbitTemplate;

    public ResumenProductorService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarResumen(ResumenInscripcion resumen) {
        try {
            rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_RESUMENES,
                RabbitMQConfig.ROUTING_KEY_RESUMENES,
                resumen.getNumeroResumen() + "|" + resumen.getEstudiante() + "|" + resumen.getCurso() + "|" + resumen.getFecha()
            );
            System.out.println("Resumen enviado a la cola: " + resumen.getNumeroResumen());
        } catch (Exception e) {
            System.err.println("Error al enviar resumen a la cola: " + e.getMessage());
            throw e;
        }
    }
}

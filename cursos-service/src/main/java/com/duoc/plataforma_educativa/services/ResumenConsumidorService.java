package com.duoc.plataforma_educativa.services;

import com.duoc.plataforma_educativa.config.RabbitMQConfig;
import com.duoc.plataforma_educativa.model.ResumenInscripcionOracle;
import com.duoc.plataforma_educativa.repository.ResumenInscripcionRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * Servicio consumidor que escucha la cola principal resumenes_queue,
 * publicada por el BFF, y persiste el resumen de inscripcion en la
 * tabla resumenes_procesados de Oracle Cloud. Si el procesamiento falla,
 * el mensaje es redirigido automaticamente a la Dead Letter Queue.
 *
 * @author Rafael Navarrete
 */
@Service
public class ResumenConsumidorService {

    private final ResumenInscripcionRepository repository;

    public ResumenConsumidorService(ResumenInscripcionRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_RESUMENES)
    public void consumirResumen(String mensaje) {
        try {
            System.out.println("Mensaje recibido de la cola: " + mensaje);
            String[] partes = mensaje.split("\\|");
            ResumenInscripcionOracle resumen = new ResumenInscripcionOracle(
                partes[0], // numeroResumen
                partes[1], // estudiante
                partes[2], // curso
                partes[3]  // fecha
            );
            repository.save(resumen);
            System.out.println("Resumen guardado en Oracle: " + partes[0]);
        } catch (Exception e) {
            System.err.println("Error al procesar mensaje: " + e.getMessage());
            throw new RuntimeException(e); // se envia a la DLQ
        }
    }
}

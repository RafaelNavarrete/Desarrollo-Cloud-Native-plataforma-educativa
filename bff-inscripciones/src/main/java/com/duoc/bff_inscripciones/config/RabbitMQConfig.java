package com.duoc.bff_inscripciones.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuracion de RabbitMQ. Define dos colas:
 * - resumenes_queue: cola principal donde se envian los resumenes de inscripcion
 * - resumenes_error_queue: Dead Letter Queue donde caen los mensajes que fallan
 *
 * Esta misma configuracion (mismos nombres) se declara tambien en
 * cursos-service, que es quien consume la cola principal.
 *
 * @author Rafael Navarrete
 */
@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_RESUMENES = "resumenes_queue";
    public static final String QUEUE_ERROR = "resumenes_error_queue";
    public static final String EXCHANGE_RESUMENES = "resumenes_exchange";
    public static final String EXCHANGE_ERROR = "resumenes_error_exchange";
    public static final String ROUTING_KEY_RESUMENES = "resumenes.key";
    public static final String ROUTING_KEY_ERROR = "resumenes.error.key";

    @Bean
    public Queue resumenesQueue() {
        return QueueBuilder.durable(QUEUE_RESUMENES)
                .withArgument("x-dead-letter-exchange", EXCHANGE_ERROR)
                .withArgument("x-dead-letter-routing-key", ROUTING_KEY_ERROR)
                .build();
    }

    @Bean
    public Queue resumenesErrorQueue() {
        return QueueBuilder.durable(QUEUE_ERROR).build();
    }

    @Bean
    public DirectExchange resumenesExchange() {
        return new DirectExchange(EXCHANGE_RESUMENES);
    }

    @Bean
    public DirectExchange resumenesErrorExchange() {
        return new DirectExchange(EXCHANGE_ERROR);
    }

    @Bean
    public Binding bindingResumenes() {
        return BindingBuilder.bind(resumenesQueue())
                .to(resumenesExchange())
                .with(ROUTING_KEY_RESUMENES);
    }

    @Bean
    public Binding bindingResumenesError() {
        return BindingBuilder.bind(resumenesErrorQueue())
                .to(resumenesErrorExchange())
                .with(ROUTING_KEY_ERROR);
    }
}

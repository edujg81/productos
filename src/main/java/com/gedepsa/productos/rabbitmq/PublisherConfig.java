package com.gedepsa.productos.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PublisherConfig {

	@Value("${rauxasoft.queue.name}")
	private String mensaje;
	
	@Bean
	Queue queue() {
		return new Queue(mensaje, true);
	}
}

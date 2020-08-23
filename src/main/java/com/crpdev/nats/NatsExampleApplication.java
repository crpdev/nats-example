package com.crpdev.nats;

import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.Nats;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class NatsExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(NatsExampleApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(){
		return args -> {
			// To Connect to NATS - use NATS API
			Connection connection = Nats.connect();

			// Using Connection
			// Dispatcher - To Consume/ Handle the message when it arrives into the queue

			Dispatcher dispatcher = connection.createDispatcher(message -> {
				System.out.println(String.format("Received Message [%s] from [%s] ",
						new String(message.getData(), StandardCharsets.UTF_8), message.getSubject()));
			});

			dispatcher.subscribe("com.crpdev");//Subscribe to the subject/ channel at which the message is expected
			// The subscription can be modified to use wild cards too

		};
	}

}

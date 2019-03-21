package com.example.rabbitmq.rabbitmqdemo;

import java.util.concurrent.TimeUnit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class RabbitmqdemoApplication implements CommandLineRunner {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private RabbitListener listener;

	@Override
	public void run(String... strings) throws Exception {

		rabbitTemplate.convertAndSend(RabbitConfig.getQueue_name(), "hiiii Hello from RabbitMQ");
		rabbitTemplate.convertAndSend("exchangeTester1","keyTester1","messageOfRandomTest");
		listener.getCountDownLatch().await(10000, TimeUnit.MICROSECONDS);

		SimpleMessage simpleMessage=new SimpleMessage("SimpleMessageName","SimpleMessageDescription");
		rabbitTemplate.convertAndSend("exchangeTester1","keyTester1",simpleMessage);

	}

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqdemoApplication.class, args);
	}
}

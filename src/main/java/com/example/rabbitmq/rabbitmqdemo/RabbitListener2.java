package com.example.rabbitmq.rabbitmqdemo;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class RabbitListener2 implements MessageListener {

        @Override
        public void onMessage(Message message) {
            System.out.println("Received a new message = [" + new String(message.getBody()) + "]");
        }

}

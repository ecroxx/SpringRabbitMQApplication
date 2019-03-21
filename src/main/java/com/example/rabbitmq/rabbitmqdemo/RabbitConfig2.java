package com.example.rabbitmq.rabbitmqdemo;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class RabbitConfig2 {

    private static final String TEST_QUEUE = "MyTestQueue";
    //private static final String TEST_EXCHANGE = "myTestExchange";

    @Bean
    Queue myTestQueue2() {
        return new Queue(TEST_QUEUE, false);
    }

   /* @Bean
    Exchange myTestExchange() {
        return new TopicExchange(TEST_EXCHANGE);
    }

    @Bean
    Binding queueBinding() {
        return new Binding(TEST_QUEUE, Binding.DestinationType.QUEUE, TEST_EXCHANGE, "simple", null);
    }
*/
    @Bean
    ConnectionFactory connectionFactory2() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }

    @Bean
    MessageListenerContainer messageListenerContainer2() {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory2());
        simpleMessageListenerContainer.setQueues(myTestQueue2());
        simpleMessageListenerContainer.setMessageListener(new RabbitListener2());
        return simpleMessageListenerContainer;
    }

}

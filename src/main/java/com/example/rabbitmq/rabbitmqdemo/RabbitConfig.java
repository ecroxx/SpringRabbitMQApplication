package com.example.rabbitmq.rabbitmqdemo;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    static final String queue_name = "rabbit_test_queue";

    @Bean
    public Queue queue() {
        return new Queue(queue_name, false);
    }

    @Bean
    public Queue queue1(){
        return QueueBuilder.durable("queueBuilt1")
                .exclusive()
                .autoDelete()
                .build();
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("rabbit_test_exchange");
    }

    @Bean
    public Exchange topicExchangeBuilderFunc(){
       return ExchangeBuilder.topicExchange("topicExchangeBuild1")
                .autoDelete()
                .durable(true)
                .build();
    }

    @Bean
    public Exchange headerExchangeBuilderFunc(){
       return ExchangeBuilder.headersExchange("headerExchangeBuild1")
                .autoDelete()
                .durable(true)
                .internal()
                .build();
    }

    @Bean
    public Exchange fanoutExchangeBuilderFunc(){
       return ExchangeBuilder.fanoutExchange("fanoutExchangeBuild1")
                .autoDelete()
                .durable(false)
                .internal()
                .build();
    }


    @Bean
    public Exchange directExchangeBuilderFunc(){
       return ExchangeBuilder.directExchange("directExchangeBuild1")
                .autoDelete()
                .internal()
                .build();
    }

    @Bean
    public Binding queueBinding1(){
        return new Binding("queueBuilt1",Binding.DestinationType.QUEUE,"topicExchangeBuild1","topic1",null);
    }
    @Bean
    public Binding queueBinding() {
        return BindingBuilder.bind(queue())
                .to(topicExchange())
                .with(queue_name);
    }

    @Bean
    SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory,
                                                            MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer messageListenerContainer = new SimpleMessageListenerContainer();
        messageListenerContainer.setConnectionFactory(connectionFactory);
        messageListenerContainer.setMessageListener(listenerAdapter);
        messageListenerContainer.setQueueNames(queue_name);
        return messageListenerContainer;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(RabbitListener rabbitListener) {
        return new MessageListenerAdapter(rabbitListener, "receiveMessage");
    }

    public static String getQueue_name() {
        return queue_name;
    }
}

package com.example.rabbitmq.rabbitmqdemo;

import java.util.concurrent.CountDownLatch;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println("Received a new message = [" + new String(message.getBody()) + "]");
    }
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        System.out.println("message = [" + message + "]");
        countDownLatch.countDown();
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }
}

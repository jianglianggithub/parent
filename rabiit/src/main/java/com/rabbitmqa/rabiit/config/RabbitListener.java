package com.rabbitmqa.rabiit.config;

import org.springframework.amqp.core.Message;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;

@Configuration
public class RabbitListener {


    @org.springframework.amqp.rabbit.annotation.RabbitListener(queues = "sxQueue")
    public void a(Message message){
        System.out.println(new String(message.getBody()));
    }


    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<Object> queue = new LinkedBlockingQueue<>(5);

        queue.put("aa");

    }
}

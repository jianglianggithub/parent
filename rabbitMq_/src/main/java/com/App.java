package com;


import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@SpringBootApplication

public class App {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(App.class);


    }

   // @RabbitListener(queues = {"queue3"})
    public void aa(Message message) throws UnsupportedEncodingException {
        byte[] body = message.getBody();
        System.out.println(new String(body,"utf-8"));
    }

}

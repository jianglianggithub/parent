package com.rabbitmqa.rabiit;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class RabiitApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabiitApplication.class, args);
    }


    @Bean

    public Queue sxQueue(){
        Map<String,Object> map=new HashMap<>();
        map.put("x-dead-letter-exchange","sxExchange");
        map.put("x-max-length",200);
        return QueueBuilder.durable("sxQueue").withArguments(map).build();
    }

    @Bean
    public Exchange sxExchange(){
        return ExchangeBuilder.fanoutExchange("sxExchange").build();
    }


    @Bean
    public Queue queue1(){
       return QueueBuilder.durable("putongQueue").build();
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue1()).to(sxExchange()).with("a").noargs();
    }
}

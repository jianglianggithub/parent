package com;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfigUration2 {



    @Bean
    public Queue queue1(){
        return new Queue("q1",true,false,false);
    }
    @Bean
    public Queue queue2(){
        return new Queue("q2",true,false,false);
    }
//
//    @Bean
//    public Exchange exchange1(){
//        return ExchangeBuilder.fanoutExchange("jiaohuanji1").build();
//    }
    @Bean
    public Exchange exchange2(){
        return ExchangeBuilder.topicExchange("jiaohuanji2").build();
    }
//    @Bean
//    public Binding binding(){
//        return BindingBuilder.bind(queue1()).to(exchange1()).with("").noargs();
//    }

    @Bean
    public Binding binding2(){
        return BindingBuilder.bind(queue1()).to(exchange2()).with("#.sms").noargs();
    }
    @Bean
    public Binding binding3(){
        return BindingBuilder.bind(queue2()).to(exchange2()).with("#.mail").noargs();
    }
}

package com;


import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

//@Configuration
public class RabbitConfigUration {



    @Bean
    public Queue queue1() {
        Map<String, Object> map = new HashMap<>();
        map.put("x-dead-letter-exchange", "sxExchange");
        map.put("x-max-length", 200);
        // 指定   交换机 进行路由时 的routingKey  在 简单模式下  必须指定routeingKey否则无法转发
        map.put("x-dead-letter-routing-key","");
        return new Queue("queue1", true, false, false, map);
    }

    @Bean
    public Exchange exchange(){
        return ExchangeBuilder.directExchange("sxExchange").build();
    }

    @Bean
    public Queue queue2(){
        return new Queue("queue2",true,false,false);
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue2()).to(exchange()).with("").noargs();
    }


    @Bean
    public Queue queue3() {
        Map<String, Object> map = new HashMap<>();
        map.put("x-dead-letter-exchange", "sxExhange2");
        map.put("x-message-ttl", 1000);
        // 指定   交换机 进行路由时 的routingKey  在 简单模式下  必须指定routeingKey否则无法转发
        return new Queue("queue3", true, false, false, map);
    }
//
//    @Bean
//    public Exchange exchange1(){
//        return ExchangeBuilder.fanoutExchange("test").build();
//    }
//

   @Bean
   public Queue queue4(){
        return new Queue("Queue4",true,false,false);
   }

    @Bean
    public Binding binding1(){
        return BindingBuilder.bind(queue4()).to(exchangea()).with("").noargs();
    }
//
//    @Bean
//    public Queue topicQueue(){
//        return new Queue("topicQueue",true,false,false);
//    }
//
    @Bean
    public Exchange exchangea(){
        return ExchangeBuilder.fanoutExchange("sxExhange2").build();
    }
//
//    @Bean
//    public Binding bindingBuilder1(){
//        return BindingBuilder.bind(topicQueue()).to(exchangea()).with("#.test.#").noargs();
//    }


}

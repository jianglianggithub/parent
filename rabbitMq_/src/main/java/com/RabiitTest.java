package com;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by aa on 2019/11/13.
 */

//@Configuration
public class RabiitTest {




    @Bean
    public Queue queue(){
        return new Queue("d1");
    }

    @Bean
    public Exchange exchange(){
        return ExchangeBuilder.fanoutExchange("d1Exchange").build();
    }

    @Bean
    public Binding binding1(){
        return BindingBuilder.bind(queue()).to(exchange()).with("aaaaa").noargs();
    }



}

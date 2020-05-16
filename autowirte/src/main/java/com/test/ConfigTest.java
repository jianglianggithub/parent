package com.test;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class ConfigTest {

    @Value("${key.username}")
    String username;


    @Value("${key.password}")
    String password;


    @Bean
    public Object invoke(){
        System.out.println(username);
        System.out.println(password);


        return new Object();
    }




}

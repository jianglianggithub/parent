package com;


import com.controller.Co;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

public class Appg {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Appg.class);
        Object co = run.getBean(Co.class);
        System.out.println(co);
    }
}

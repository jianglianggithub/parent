package com;

//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.lang.reflect.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
//@MapperScan(basePackages = "com.com.mapper")
public class App {

    private List<Thread> list;

    public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException {
        SpringApplication.run(App.class);
    }


//    @Bean
//    public InternalResourceViewResolver resourceViewResolver(){
//        final InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
//        //internalResourceViewResolver.setPrefix("/");
//        //internalResourceViewResolver.setSuffix(".html");
//        return internalResourceViewResolver;
//    }



}

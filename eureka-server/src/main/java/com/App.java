package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableEurekaServer
public class App {
    String username = "zzz";
    String password = "bbb";
    public static void main(String[] args) {
        List<Pojo> list=new ArrayList();
        final Map<String, List<Pojo>> collect = list.stream().collect(Collectors.groupingBy(Pojo::getUsername));
        SpringApplication.run(App.class);
    }

}

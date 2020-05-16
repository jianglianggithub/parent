package com.controller;


import com.feign.FeignClient2;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class Client1Controller {

    @Autowired
    FeignClient2 feignClient2;

    @HystrixCommand(fallbackMethod = "client1Exception")
    @RequestMapping("/client2")
    public String client1(){
        int i=1/0;
        return feignClient2.client1();
    }

    public String client1Exception(){
        return "client1Exception";
    }

}

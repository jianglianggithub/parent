package com.controller;


import com.feign.FeignClient1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class Client1Controller {

    @Autowired
    FeignClient1 FeignClient1;

    @RequestMapping("/client1")
    public String client1(){
        return "这里是client1";
    }
}

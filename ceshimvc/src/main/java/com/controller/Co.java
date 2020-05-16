package com.controller;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/asdasd")

public class Co {

    @RequestMapping("/aaa")
    public Object aa(){
        return "asdasd";
    }
}

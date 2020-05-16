package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller
//@RequestMapping("/c2")
public class TestController2 {

    @RequestMapping("/test")
    public void c2(){
        System.out.println("c2 - test");

    }
}

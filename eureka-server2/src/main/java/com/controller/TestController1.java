package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TestController1 {
    @RequestMapping("/test")
    public String c1(){
       // map.addAttribute("a","a");
        System.out.println("c1 - test");
        return "a";
    }
}

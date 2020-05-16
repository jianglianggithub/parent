package com.controller;


import com.server.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestConroller {


    @Autowired
    TestService testService;


    @RequestMapping("/a")
    public void t(){
        testService.test1();
    }

}

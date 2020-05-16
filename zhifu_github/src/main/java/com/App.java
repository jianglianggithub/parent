package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by aa on 2019/11/3.
 */
@SpringBootApplication

@RestController

public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }

    @RequestMapping("/**")
    public Object a(HttpServletRequest request){
        return request.getRequestURL() ;}
}

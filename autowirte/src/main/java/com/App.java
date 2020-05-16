package com;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@SpringBootApplication

public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }
    @Bean(name = "jsonView")
    public MappingJackson2JsonView jsonView() {
        return new MappingJackson2JsonView();
    }

}

package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;



//@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter{

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(new ValidateLoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/user/**");
        super.addInterceptors(registry);
    }
}
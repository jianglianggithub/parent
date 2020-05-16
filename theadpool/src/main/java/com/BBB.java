package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 *   springboot 会自动 解析  --xxxxxx.xxxx=2   有2个横杠的 参数
 *
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = {"aaa"})
public class BBB  extends SpringBootServletInitializer {

    @Override//为了打包springboot项目
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }


    public static void main(String[] args) throws Exception {

        SpringApplication.run(BBB.class);



    }
}

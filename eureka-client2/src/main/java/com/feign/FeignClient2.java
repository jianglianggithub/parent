package com.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("CLIENT1")
public interface FeignClient2 {


    @RequestMapping("/client1")
    public String client1();
}

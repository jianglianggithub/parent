package com.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("CLIENT2")
public interface FeignClient1 {


    @RequestMapping("/client2")
    public String client2(String param);
}

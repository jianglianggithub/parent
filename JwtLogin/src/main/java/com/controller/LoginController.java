package com.controller;


import com.utils.JwtHelper;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.jar.JarFile;


@RestController
@RequestMapping("/user")
public class LoginController {


    @Autowired
    StringRedisTemplate redisTemplate;


    @RequestMapping("/login")
    public Object login(){
        String jwt= JwtHelper.generateJWT("123","123","ROLE_ADMIN");
        redisTemplate.opsForHash().put("user","123",jwt);
        redisTemplate.expire("user",7, TimeUnit.DAYS);
        return jwt;
    }

    @RequestMapping("/a")
    public void aa(){
        redisTemplate.opsForGeo().add("position",new Point(114.305215,30.592935),"北京");
        redisTemplate.opsForGeo().add("position",new Point(112.23813,30.326857),"荊州市");

        //計算 2點之間距離
        Distance distance = redisTemplate.opsForGeo().distance("position", "北京", "荊州市", RedisGeoCommands.DistanceUnit.KILOMETERS);
        //批量獲取
        List<Point> pointList = redisTemplate.opsForGeo().position("position", "北京", "荊州市");
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().
                includeDistance().
                includeCoordinates()
                .sortAscending()
                .limit(5);

        
        GeoResults<RedisGeoCommands.GeoLocation<String>> position = redisTemplate.opsForGeo().radius("position", new Circle(new Point(112.23813, 30.326857),
                new Distance(500, RedisGeoCommands.DistanceUnit.KILOMETERS)), args);
        for (GeoResult<RedisGeoCommands.GeoLocation<String>> entry : position) {
        }
        System.out.println(position);
    }


    public static void main(String[] args) throws IOException {
        String[] cps = System.getProperty("java.class.path").split(";");
        String resourcePath="META-INF/spring.factories";
        Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(resourcePath);
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            System.out.println(url);
        }

    }

}

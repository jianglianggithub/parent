package com;

import com.github.wxpay.sdk.WXPayUtil;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BBB {
    Map<String,Object> locks=new ConcurrentHashMap<>();

    public static void main(String[] args) throws Exception {
        InputStream inputStream=null;
        List<String> rs = IOUtils.readLines(inputStream);
        String str="";
        for (String r : rs) {
            str+=r;
        }
        Map<String, String> stringStringMap = WXPayUtil.xmlToMap(str);
    }


    private static void test1(String[] ...ar) {
    }

    public  void test(String userId) throws InterruptedException {

        Object monitor = locks.computeIfAbsent(userId, k -> new Object());

        synchronized (monitor) {

            //业务代码快 忽略...
            System.out.println("业务代码执行完毕"+Thread.currentThread().getName());
            Thread.sleep(6000);
            throw new RuntimeException();

        }
    }


}

package com;


import com.controoler.C;
import com.controoler.IProxyTagert;
import com.controoler.ProxyTagert;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@SpringBootApplication
public class App {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final Method aaa = IProxyTagert.class.getMethod("AAA");
        final Method aaa1 = C.class.getMethod("AAA");
        aaa1.invoke(new ProxyTagert());
        // 父类的方法子类可以执行   子类的方法 如果在重写了的情况下  父类无法执行 除非没有重写
    }
}

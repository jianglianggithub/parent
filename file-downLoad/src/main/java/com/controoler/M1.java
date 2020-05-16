package com.controoler;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;

public class M1 implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("1111");
        return null;
    }
}

class M2 implements MethodInterceptor{

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("222222222");
        return null;
    }

    public static void main(String[] args) throws IOException {

        final URL resource = Thread.currentThread().getContextClassLoader().getResource("");
        File[] files = new File(resource.getPath() + "com" + File.separator + "controoler").listFiles();
        System.out.println(files.length);
    }
}

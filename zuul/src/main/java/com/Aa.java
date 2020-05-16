package com;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ServiceLoader;

public interface Aa {


    @DataSource("aaa")
    void as();


    public static void main(String[] args) {
        ServiceLoader<Aa> load = ServiceLoader.load(Aa.class);
        load.forEach(item -> item.as());
    }
}

package com;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

public class DefualtFutre<T> extends CompletableFuture<T> {

    private int id;
    public Object resu;
    private static final AtomicInteger integer=new AtomicInteger(0);
    public static final Map<Integer,DefualtFutre> result=new HashMap<>();


    public DefualtFutre(){
        id=integer.getAndIncrement();
    }


}

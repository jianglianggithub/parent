package com.demo1.myFilterChian;

public class Handler1 implements Handler {
    @Override
    public void register(Context context) {
        System.out.println("handler 1");
        context.findPreContextInvocation();
    }
}

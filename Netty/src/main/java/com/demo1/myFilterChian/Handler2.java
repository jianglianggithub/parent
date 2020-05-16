package com.demo1.myFilterChian;

public class Handler2 implements Handler {
    @Override
    public void register(Context context) {
        System.out.println("handler 2");
        context.findPreContextInvocation();
    }
}

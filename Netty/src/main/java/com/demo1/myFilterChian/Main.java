package com.demo1.myFilterChian;

public class Main {

    static Context context=new Context();
    static {
        context.handler=new Handler1();
        final Context context1 = new Context();
        context1.handler=new Handler2();
        context.next=context1;
    }
    public static void main(String[] args) {
        context.handler.register(context);
    }


}

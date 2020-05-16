package com.demo1.myFilterChian;

public class Context {

    public Handler handler;
    public Context next;


    public void findPreContextInvocation() {
        Context next =null;
        if (this.next!= null){
            next=this.next;
            next.handler.register(next);
        }
    }
}

package com.责任链.netty;

public abstract class AbstractHandleContext {



    protected AbstractHandleContext pre;
    protected AbstractHandleContext next;



    public abstract Handle getHandle();


    public void nextRead(Object o){
        if (next != null ){
            next.getHandle().channelRead(next,o);
        }
    }
}

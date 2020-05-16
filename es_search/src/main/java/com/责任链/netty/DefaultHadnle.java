package com.责任链.netty;

public class DefaultHadnle implements Handle {
    @Override
    public void channelRead(AbstractHandleContext ctx,Object o) {
        System.out.println("DefaultHadnle read"+o);
    }

    @Override
    public void channelActive() {
        System.out.println("DefaultHadnle active");

    }

    @Override
    public void channelExsits() {
        System.out.println("DefaultHadnle exsit");

    }
}

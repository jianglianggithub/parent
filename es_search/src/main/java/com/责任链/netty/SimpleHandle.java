package com.责任链.netty;

public class SimpleHandle implements Handle {
    @Override
    public void channelRead(AbstractHandleContext ctx,Object o) {
        System.out.println("SimpleHandle read");
    }

    @Override
    public void channelActive() {
        System.out.println("SimpleHandle active");

    }

    @Override
    public void channelExsits() {
        System.out.println("SimpleHandle exsit");

    }
}

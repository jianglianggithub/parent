package com.责任链.netty;

public interface Handle {





    void channelRead(AbstractHandleContext ctx,Object o);
    void channelActive();
    void channelExsits();
}

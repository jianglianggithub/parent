package com.责任链.netty;

public class DefualtChannelHandleContext extends AbstractHandleContext {
    public Handle handle;

    @Override
    public Handle getHandle() {
        return handle;
    }
}

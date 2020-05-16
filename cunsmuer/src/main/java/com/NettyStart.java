package com;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.stereotype.Component;


@Component
public class NettyStart implements ApplicationRunner {



    EventLoopGroup worker;
    Bootstrap bootstrap;
    ChannelFuture channelFuture;

    public EventLoopGroup getWorker() {
        return worker;
    }

    public void setWorker(EventLoopGroup worker) {
        this.worker = worker;
    }

    public Bootstrap getBootstrap() {
        return bootstrap;
    }

    public void setBootstrap(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    public ChannelFuture getChannelFuture() {
        return channelFuture;
    }

    public void setChannelFuture(ChannelFuture channelFuture) {
        this.channelFuture = channelFuture;
    }

    public InitHandle getInitHandle() {
        return initHandle;
    }

    public void setInitHandle(InitHandle initHandle) {
        this.initHandle = initHandle;
    }

    @Autowired
    InitHandle initHandle;

    public NettyStart(){

        this.worker=new NioEventLoopGroup();
        this.bootstrap=new Bootstrap().group(worker)
                .channel(NioSocketChannel.class);
    }



    @Override
    public void run(ApplicationArguments args) throws Exception {
        bootstrap.handler(initHandle);
        this.channelFuture= bootstrap.connect("127.0.0.1",2333);
    }


}

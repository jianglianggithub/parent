package com;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class NettyStart implements ApplicationRunner {


    EventLoopGroup bos;
    EventLoopGroup worker;
    ServerBootstrap serverBootStarp;
    ChannelFuture channelFuture;
    @Autowired
    InitHandle initHandle;

    public NettyStart(){
        this.bos=new NioEventLoopGroup(1);
        this.worker=new NioEventLoopGroup();
        this.serverBootStarp=new ServerBootstrap().group(bos,worker)
                .channel(NioServerSocketChannel.class);
    }



    @Override
    public void run(ApplicationArguments args) throws Exception {
        serverBootStarp.childHandler(initHandle);
        this.channelFuture= serverBootStarp.bind(2333);
    }


}

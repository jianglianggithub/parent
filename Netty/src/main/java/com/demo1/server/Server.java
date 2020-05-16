package com.demo1.server;

import com.sun.deploy.trace.LoggerTraceListener;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.internal.logging.InternalLogLevel;

public class Server {
    public static void main(String[] args) throws Exception {



        final NioEventLoopGroup bos = new NioEventLoopGroup(1);
        final NioEventLoopGroup woker = new NioEventLoopGroup();
        final ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .channel(NioServerSocketChannel.class)
                .group(bos,woker)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .childHandler(new ServerAcceptHandler());
        final ChannelFuture channelFuture = serverBootstrap.bind(2333).sync();

        //  获取 对应得通道  由异步得等待 对方退出变为 同步等待
        channelFuture.channel().closeFuture().sync();

        bos.shutdownGracefully();
        woker.shutdownGracefully();
    }
}

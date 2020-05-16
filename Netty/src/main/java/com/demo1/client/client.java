package com.demo1.client;

import com.demo1.server.ServerAcceptHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.spi.SelectorProvider;
import java.util.Scanner;

public class client {
    public static void main(String[] args) throws Exception {




        final NioEventLoopGroup woker = new NioEventLoopGroup();
        final Bootstrap serverBootstrap = new Bootstrap();
        serverBootstrap
                .channel(NioSocketChannel.class)
                .group(woker)
                .handler(new ClientAcceptHandler());

        final ChannelFuture localhost = serverBootstrap.connect(new InetSocketAddress("localhost", 2333)).sync();

        final Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()){
            final String next = scanner.next();
            localhost.channel().writeAndFlush(next+"\n");
        }
        //  获取 对应得通道  由异步得等待 对方退出变为 同步等待
        localhost.channel().closeFuture().sync();


        woker.shutdownGracefully();
    }
}

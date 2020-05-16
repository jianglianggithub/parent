package com.udp;


import io.netty.bootstrap.Bootstrap;

import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;


import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;

import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * Created by aa on 2019/11/11.
 */
public class server {

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup bos = new NioEventLoopGroup();
        Bootstrap bootstrap  = new Bootstrap();
        bootstrap.group(bos).channel(NioDatagramChannel.class)
                .option(ChannelOption.SO_BROADCAST, true)
                .handler(new SimpleChannelInboundHandler<DatagramPacket>() {

                    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
                        String s = msg.content().toString(Charset.defaultCharset());
                        System.out.println("upd 客户端发的消息"+s);
                        System.out.println(msg.sender());
                        ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("RESULT:" + "aaa" ,CharsetUtil.UTF_8), msg.sender()));;
                    }
                });
        Channel channel = bootstrap.bind(7777).sync().channel();

    }
}

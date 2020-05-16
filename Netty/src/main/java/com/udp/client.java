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

/**
 *  在netty中 客户端 也需要启动 ， 只是启动后以 udp协议发送 内容 【无链接】
 */
public class client {
    public static void main(String[] args) {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.group(eventLoopGroup).channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .handler(new SimpleChannelInboundHandler<DatagramPacket>() {
                        @Override
                        protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
                            System.out.println(msg.content().toString(Charset.defaultCharset()));
                        }
                    });
            Channel channel = bootstrap.bind(8889).sync().channel();
            channel.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("QUERY", CharsetUtil.UTF_8), new InetSocketAddress("255.255.255.255", 7777))).sync();
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}

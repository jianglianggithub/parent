package com.demo1.server;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerSocketEvent extends ChannelInboundHandlerAdapter{

    //构建链接组
    public static DefaultChannelGroup defaultChannelGroup=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    List list=new ArrayList();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Added"+" 存活个数 "+defaultChannelGroup.size());
        list.add(ctx.channel());
        //当有链接加入
        System.out.println(defaultChannelGroup.hashCode());
        defaultChannelGroup.writeAndFlush("[ 服务器 推送 通知 ]");
        defaultChannelGroup.add(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println(defaultChannelGroup.size()+"    "+list.size());

        defaultChannelGroup.forEach(channel ->
        {
            if (ctx.channel()==channel){
                channel.writeAndFlush("[ 这条消息来自自己发出 ]"+msg+"\n");
            }else {
                channel.writeAndFlush("[ 推送其他链接人消息 ]"+msg+"\n");
            }
        });

    }


    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

        defaultChannelGroup.writeAndFlush("[服务器推送下线通道]"+ctx.channel().remoteAddress()+"\n");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

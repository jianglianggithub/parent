package com;

import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;


@Component
public class InitHandle extends ChannelInitializer<SocketChannel> {


    @Autowired
    Exc exc;

    public Map map=new HashMap();
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline()
                .addLast(new LineBasedFrameDecoder(65536))
                .addLast(new StringDecoder())
                .addLast(new StringEncoder())
                .addLast(new SimpleChannelInboundHandler<String>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
                        System.out.println("收到结果"+msg);
                        LinkedBlockingQueue<Runnable> queue = exc.queue;
                        queue.put(()->{
                            DefualtFutre.result.get(1).resu=msg;
                        });
                    }
                });
    }
}

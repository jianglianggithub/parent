package com.demo1.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class ServerAcceptHandler extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        //                                                         最大长度，长度feild的偏移量，长度feild的长度，长度feild的偏移量，content起始位置抛弃之前的几个字节【长度feild的长度】
        //new LengthFieldBasedFrameDecoder(1024,0,4,0,4);
        socketChannel.pipeline()
                .addLast(new DelimiterBasedFrameDecoder(1024, Delimiters.lineDelimiter()))
                .addLast(new StringDecoder())
                .addLast(new StringEncoder())
                .addLast(new ServerSocketEvent());
    }
}

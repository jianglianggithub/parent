package com.protobuf;

import com.AddressBookProtos;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * Created by aa on 2019/11/13.
 */
public class client {

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup work = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();

        bootstrap.channel(NioSocketChannel.class)
                .group(work)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ProtobufVarint32FrameDecoder())
//                            .addLast(new ProtobufDecoder(Person.getDefaultInstance()))
                                .addLast(new ProtobufDecoder(AddressBookProtos.Person.getDefaultInstance())  )
                                .addLast(new ProtobufVarint32LengthFieldPrepender())
                                .addLast(new ProtobufEncoder())
                                .addLast(new SimpleChannelInboundHandler<AddressBookProtos.Person>() {
                                    @Override
                                    protected void channelRead0(ChannelHandlerContext ctx, AddressBookProtos.Person msg) throws Exception {
                                        System.out.println(msg);
                                        AddressBookProtos.Person.Builder a = msg.toBuilder().setMath("客户端嘻嘻嘻嘻嘻嘻嘻嘻");
                                        ctx.channel().writeAndFlush(a);
                                    }

                                    @Override
                                    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                        super.exceptionCaught(ctx, cause);
                                    }
                                });
                    }
                });

        ChannelFuture future = bootstrap.connect("localhost", 9999).sync();
        future.channel().closeFuture().sync();
    }
}

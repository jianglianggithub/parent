package com.protobuf;

import com.AddressBookProtos;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * Created by aa on 2019/11/13.
 */
public class server {

    public static void main(String[] args) {

        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {

                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(new ProtobufVarint32FrameDecoder())
//                    .addLast(new ProtobufDecoder(Person.getDefaultInstance()))
                                    .addLast(new ProtobufDecoder(AddressBookProtos.Person.getDefaultInstance()))
                                    .addLast(new ProtobufVarint32LengthFieldPrepender())
                                    .addLast(new ProtobufEncoder())
                                    .addLast(new SimpleChannelInboundHandler<AddressBookProtos.Person>() {

                                        @Override
                                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                            AddressBookProtos.Person.Builder personBulider = AddressBookProtos.Person.newBuilder();
                                            AddressBookProtos.Person.PhoneType phoneType = AddressBookProtos.Person.PhoneType.HOME;
                                            AddressBookProtos.Person.PhoneNumber.Builder phoneNumber = AddressBookProtos.Person.PhoneNumber.newBuilder();
                                            phoneNumber.setNumber(phoneType);
                                            personBulider.setPhones(phoneNumber);
                                            personBulider.setMath("aaa");
                                            personBulider.setCompute("BB");
                                            AddressBookProtos.Person build = personBulider.build();
                                            ctx.channel().writeAndFlush(build);
                                        }

                                        @Override
                                        protected void channelRead0(ChannelHandlerContext ctx, AddressBookProtos.Person people) throws Exception {
                                            System.out.println(people.getMath());

                                        }
                                    });
                        }

                    });

            ChannelFuture future = serverBootstrap.bind(9999).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            bossGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}

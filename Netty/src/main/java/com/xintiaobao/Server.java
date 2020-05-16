package com.xintiaobao;

import com.demo1.server.ServerAcceptHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

public class Server {
    public static void main(String[] args) throws Exception {
        final NioEventLoopGroup bos = new NioEventLoopGroup();
        final NioEventLoopGroup woker = new NioEventLoopGroup();
        final ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .channel(NioServerSocketChannel.class)
                .group(bos,woker)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel
                                .pipeline()
                                .addLast(
                                        new IdleStateHandler(3,5,7),
                                        new ChannelInboundHandlerAdapter(){
                                            @Override
                                            public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                                                IdleStateEvent idleStateEvent =(IdleStateEvent)evt;
                                                final IdleState state = idleStateEvent.state();
                                                if (state==IdleState.READER_IDLE){
                                                    System.out.println(" 长时间 没有读取");
                                                }else if (state==IdleState.WRITER_IDLE){
                                                    System.out.println("长时间 没有写入数据");
                                                }else {
                                                    System.out.println("长时间未读写");
                                                }
                                            }
                                        }
                                );
                    }
                });
        final ChannelFuture channelFuture = serverBootstrap.bind(2333).sync();

        //  获取 对应得通道  由异步得等待 对方退出变为 同步等待
        channelFuture.channel().closeFuture().sync();

        bos.shutdownGracefully();
        woker.shutdownGracefully();
    }
}

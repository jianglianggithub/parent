package com.webSocket;

import com.demo1.server.ServerAcceptHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandler;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Date;
import java.util.Scanner;

public class Server {

    public static DefaultChannelGroup defaultChannelGroup=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

        public static void main(String[] args) throws Exception {
            AttributeKey<Integer> aa = AttributeKey.valueOf("aa");
            final NioEventLoopGroup bos = new NioEventLoopGroup();
            final NioEventLoopGroup woker = new NioEventLoopGroup();
            final ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap
                    .channel(NioServerSocketChannel.class)
                    .group(bos,woker)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline()
                                    .addLast(
                                            new HttpServerCodec(),
                                            new ChunkedWriteHandler(),
                                            new HttpObjectAggregator(65535),
                                            new WebSocketServerProtocolHandler("/ws"),
                                            new IdleStateHandler(30,50,60),
                                            new SimpleChannelInboundHandler<TextWebSocketFrame>() {
                                                @Override
                                                public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

                                                    defaultChannelGroup.add(ctx.channel());
                                                }

                                                @Override
                                                protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame message) throws Exception {
                                                    System.out.println("client message"+message.text());
                                                    channelHandlerContext.writeAndFlush(new TextWebSocketFrame(new Date().toString()));
                                                }

                                                @Override
                                                public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
                                                    System.out.println(ctx.channel().remoteAddress()+"下线");
                                                }

                                                @Override
                                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                                    System.out.println(ctx.channel().remoteAddress());
                                                }

                                                @Override
                                                public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                                                    if (evt instanceof IdleStateEvent){
                                                        IdleStateEvent event=(IdleStateEvent)evt;
                                                        if (event.state()== IdleState.READER_IDLE){
                                                            System.out.println("读空闲");
                                                        }else if (event.state()==IdleState.WRITER_IDLE){
                                                            System.out.println("写空闲");
                                                        }
                                                        // 异步关闭 无法解决 如果客户端直接开启飞行模式得情况下
                                                        //是无法感知到 客户端下线得，会内存泄露 白白占用空间  等飞行模式换成正常 游览器才会发起关闭  那么这个时间 中途是非常得 浪费资源得
                                                        // 所以检测到空闲 服务端 应该直接关闭链路
                                                        ctx.channel().close();

                                                        //这种方式 会一直等到 客户端 或者游览器端 发起一个 close 包 那么 服务端才会 关闭链接
                                                        // 如果 直接飞行模式下  是不会发出close 得操作得 那么 服务端 是无法感知到 客户端 下线得
                                                        // close操作其实会发一个包 然后 客户端在close 服务端收到 close包后 在close得
                                                        // 所以 在直接飞行模式下 没有close 那么链接是无法关闭得
                                                        // 只有等待 重联网后 游览器才会发起 close包
                                                        //ctx.channel().closeFuture();
                                                    }
                                                }
                                            }

                                    );
                        }
                    });
            final ChannelFuture channelFuture = serverBootstrap.bind(2333).sync();
            final ChannelFuture sync = serverBootstrap.bind(2444).sync().sync();

            final Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()){
                System.out.println(defaultChannelGroup.size()+"==== channel得数量");
                final String next = scanner.next();
                defaultChannelGroup.writeAndFlush(new TextWebSocketFrame(next));
            }
            //  获取 对应得通道  由异步得等待 对方退出变为 同步等待
            channelFuture.channel().closeFuture().sync();

            bos.shutdownGracefully();
            woker.shutdownGracefully();
        }

}

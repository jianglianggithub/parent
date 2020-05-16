package com;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;


@Component
public class Exc {


    public LinkedBlockingQueue<Runnable> queue=new LinkedBlockingQueue<Runnable>();


    public DefualtFutre get(int i) throws InterruptedException {

        System.out.println("开始等待");
        Runnable poll = queue.take();
        System.out.println("结果收到");
        poll.run();

        DefualtFutre defualtFutre = DefualtFutre.result.get(i);
        System.out.println("调用的结果为"+defualtFutre.resu);
        return defualtFutre;
    }

    public void exce(ApplicationContext context) throws InterruptedException {
        NettyStart nettyStart =  (NettyStart) context.getBean("nettyStart");
        DefualtFutre<Object> futre = new DefualtFutre<>();

        nettyStart.channelFuture.channel().writeAndFlush("1");
        get(1);
    }
}

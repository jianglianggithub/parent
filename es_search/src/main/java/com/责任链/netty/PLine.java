package com.责任链.netty;

import org.apache.commons.math3.analysis.function.Abs;

import java.util.concurrent.locks.ReentrantLock;

public class PLine {

    private AbstractHandleContext heard;
    private AbstractHandleContext tail;





    public PLine() {

        heard=new HeadContext();
        tail=new TailContext();
        heard.next=tail;
        tail.pre=heard;
    }

    public void add(AbstractHandleContext ctx) {
        AbstractHandleContext pre = tail.pre;
        pre.next=ctx;
        ctx.pre=pre;
        ctx.next=tail;
        tail.pre=ctx;
    }

    public void startRead(){
        heard.getHandle().channelRead(heard,"msg");
    }

    private AbstractHandleContext getNode(AbstractHandleContext pre) {

        AbstractHandleContext node=pre;
        while (node.next != tail ){
            node=node.next;
        }

        return node;
    }

    public static void main(String[] args) {
        PLine pLine = new PLine();
        DefualtChannelHandleContext a = new DefualtChannelHandleContext();
        a.handle=new Handle() {
            @Override
            public void channelRead(AbstractHandleContext ctx, Object o) {
                System.out.println(o);
                ctx.nextRead(o);
            }

            @Override
            public void channelActive() {

            }

            @Override
            public void channelExsits() {

            }
        };
        pLine.add(a);
        pLine.startRead();
    }





    class HeadContext extends AbstractHandleContext implements Handle{

        @Override
        public Handle getHandle() {
            return this;
        }

        @Override
        public void channelRead(AbstractHandleContext ctx,Object o) {
            System.out.println(" headContext Read");
            ctx.nextRead(o);
        }

        @Override
        public void channelActive() {
            System.out.println(" headContext Active");

        }

        @Override
        public void channelExsits() {
            System.out.println(" headContext Exsits");

        }
    }



    class TailContext extends AbstractHandleContext implements Handle{

        @Override
        public Handle getHandle() {
            return this;
        }

        @Override
        public void channelRead(AbstractHandleContext ctx,Object o) {
            System.out.println(" TailContext Read");
            ctx.nextRead(o);
        }

        @Override
        public void channelActive() {
            System.out.println(" TailContext Active");

        }

        @Override
        public void channelExsits() {
            System.out.println(" TailContext Exsits");

        }
    }

}

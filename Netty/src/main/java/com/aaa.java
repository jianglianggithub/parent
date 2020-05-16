package com;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.internal.PlatformDependent;
import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class aaa {
    public volatile int a;




    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InterruptedException {
        int modifiers = aaa.class.getModifiers();
        boolean aPublic = Modifier.isPublic(modifiers);

        final aaa aaa = new aaa();


        AtomicIntegerFieldUpdater<aaa> updater = AtomicIntegerFieldUpdater.newUpdater(aaa.class, "a");

        for (int i = 0; i < 10; i++) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (;;){
                         int test=aaa.a;
                         int test1=test+1;
                         if (updater.compareAndSet(aaa,test,test1)){
                             break;
                         }
                    }
                }
            }).start();
        }

        Thread.sleep(1000);

        System.out.println(aaa.a);



    }


    @Test
    public void test1(){
        PooledByteBufAllocator pooledByteBufAllocator = new PooledByteBufAllocator(PlatformDependent.directBufferPreferred());

        // 当使用 netty 的池化堆外内存 创建bytebuf的时候。会采用引用计数的方式 来判断是否 回收对象
        // 创建的时候 默认就是引用计数=1
        ByteBuf buffer = pooledByteBufAllocator.buffer();
        buffer.duplicate();
        // relesase 减一
        buffer.release();

        // 在netty中 只要实现了 ReferenceCounted 接口的 类 对象 逗可以 使用 手动回收机制

        //如果是 堆内 不做处理 只显式申明为null


    }



    @Test
    public void test2(){

        ByteBuf byteBuf = Unpooled.copiedBuffer("abcdefghijklmn".getBytes());

        ByteBuf byteBuf1 = byteBuf.readSlice(5); //共享  引用计数
        byteBuf1.release();
        System.out.println(byteBuf.refCnt()+"   "+byteBuf1.refCnt());
        ByteBufAllocator allocator = new NioServerSocketChannel().config().getAllocator();


    }


}



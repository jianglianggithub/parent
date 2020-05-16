package com.yibu;

import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

public class ServiceThread {


    private AtomicBoolean notify=new AtomicBoolean(false);
    private volatile boolean stop = false;
    private CountDownLatch countDownLatch=new CountDownLatch(1);


    public static void main(String[] args) throws InterruptedException {


        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(10);
        byteBuffer.position(5);
        ByteBuffer slice = byteBuffer.slice();
        slice.position(6);
        System.out.println();


        System.exit(-1);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    LockSupport.park();
                    System.out.println("1");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        Thread.sleep(20);
        LockSupport.unpark(thread);
    }

    public void shutdown() {
        stop=true;
    }

    public void watting() {
        if ( notify.compareAndSet(true,false) ) {
            this.onGouzi();
            return;
        }

//        countDownLatch.reset();

        try {
            countDownLatch.await(2000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            notify.set(false);
            this.onGouzi();
        }

    }


    private void onGouzi() {
    }

}

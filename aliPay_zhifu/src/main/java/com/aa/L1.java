package com.aa;

import java.nio.ByteBuffer;
import java.time.LocalTime;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class L1 {


    public static void main(String[] args) {
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        allocate.putInt(1);
        allocate.putInt(2);
        byte[] array1 = allocate.array();

        ByteBuffer allocate1 = ByteBuffer.allocate(20);
        byte[] array = allocate.array();
        allocate1.put(array,0,allocate.position());

//        allocate.put("bbb".getBytes(),1,2);//不管是put还是get 后面的offset 表示的是 字节数组对应的 起始偏移量
//
//
//        allocate.get(5);//这种get 不会改变position  还有 指定index 的 get
//        System.out.println(allocate.position());



    }

    private static void aaa(int b) {
        System.out.println(b);
    }
}

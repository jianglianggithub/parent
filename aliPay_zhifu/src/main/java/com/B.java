package com;

import com.yibu.A;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class B extends A {


    public static void main(String[] args) throws Exception {
        RandomAccessFile rw = new RandomAccessFile(new File("d:/aaa"), "rw");
        MappedByteBuffer mmap = rw.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, 1024 * 1024 * 1024);

//        for (int i = 0; i < rw.getChannel().size(); i+= 4096) {
//            mmap.put(i,(byte)0);
//
//            if (i  == rw.getChannel().size() - 4096) {
//                System.out.println("1");
//                mmap.force();//当刷盘后 pageCache 的 缓存 就会被 转移到 swap分区了。
//                LockSupport.park();
//            }
//        }
        long size = rw.getChannel().size();
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        AtomicInteger integer= new AtomicInteger(0);
        long[] ints=new long[4];
        for (int i = 0; i < 4; i++) {
            ints[i]=(i+1) * size / 4;
        }

        for (int i = 0; i < 4; i++) {
            executorService.execute(()->{
                long end = ints[integer.getAndAdd(1)];
                int start = (int) (end - ((1024 * 1024 * 1024) / 4));
                System.out.println(start+" "+ end);
                for ( ; start < end; start++) {

                    if (start == size-1) {
                        System.out.println("ok");
                    }
                    mmap.put(start,(byte)22);
                }
                System.out.println(Thread.currentThread().getName()+"结束");
            });

        }

        Scanner scanner = new Scanner(System.in);
        scanner.hasNext();
        System.out.println("aa");
        mmap.force();
    }
    @Override
    public  void a1() {
        super.a1();
    }

    @Override
    public void getB() {
        System.out.println(" B  getb");
    }



}

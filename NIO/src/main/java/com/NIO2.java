package com;

import sun.nio.ch.DirectBuffer;

import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;

/**
 * 使用  直接缓存  缓存直接 放在 物理内存  非jvm堆内存中
 */
public class NIO2 {
    public static void main(String[] args) throws Exception {


        System.out.println(1-2*3);


        RandomAccessFile rw = new RandomAccessFile(new File("D:/1.txt"), "rw");
        FileChannel channel = rw.getChannel();

//        FileChannel out=FileChannel.open
//                (new File("d:\\aaa.mp4").toPath(),StandardOpenOption.WRITE,StandardOpenOption.CREATE,
//                        StandardOpenOption.READ);

        //返回的是 dricetBuffer 。 就是直接操作直接内存。  0拷贝。但是占用内存空间比较大。 ps：虚拟内存。
        // 参数2 = 指针位置。 参数2 等于虚拟内存的大小
        int i = 1024 * 1024 * 1024;
        MappedByteBuffer inMapper = channel.map(FileChannel.MapMode.READ_WRITE, 0, i);

        //MappedByteBuffer outMapper = out.map(FileChannel.MapMode.READ_WRITE, 0, in.size());

        //in.transferTo(index,length)out


        //outMapper.put(inMapper);
        channel.close();

        Thread.sleep(1000);
        //也就是说 内存映射 的虚拟内存文件 是跟filechannel没啥关系的。
        inMapper.position(i-1);
        inMapper.put("a".getBytes());

//        out.close();
//        inMapper.clear();
//       // outMapper.clear();
//        Thread.sleep(100);
//        System.out.println(new File("D:\\sp\\aaa.mp4").delete());


    }


}

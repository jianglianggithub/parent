package com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIO {
    public static void main(String[] args) throws Exception {
        final ByteBuffer allocate = ByteBuffer.allocate(1024); //allocate=分配

        final FileInputStream fileInputStream = new FileInputStream(new File("D:\\sp\\aaa.mp4"));
        final FileChannel channel = fileInputStream.getChannel();



        final FileOutputStream fileOutputStream = new FileOutputStream(new File("D:\\bb.doc"));

        final FileChannel channel1 = fileOutputStream.getChannel();
        // 从写入改为 读取 反转

        //  当 通过通道 这个 介质 把 字节读到 缓冲区 也就是 写入
        // 每次写入 posiontion++ 那么 当你需要 输出的时候 就把 position=limit 最大刻度长度 获取到了
        //  然后在 flip 回到起点  就可以 做读取操作 当然你也可以写入 但是没什么意义
        //   nio是面向 缓冲区的 可读 可写， 也是对 字节数组操作  每次的写会覆盖 之前的 写入字节数组的数据
        // 但是每次不一定写完 那么 怎么才能保证读取的正确性 就靠的是 position 每次读 完 定位自增1 那么就可以保证
        //
        int leng;
        while ((leng=channel.read(allocate)) >0){
            //  将posistion 归0  然后让  最大可读的数组长度 = 当前索引 其实是++ 也就是能读取的 最大 字节长度
            System.out.println("position : "+allocate.position());
            allocate.flip();

            System.out.println("limit :  "+allocate.limit());
            System.out.println("leng :  "+leng);
            // 通过 缓存区 输出到通道 的目标 地点
            channel1.write(allocate);

            // 输出完后 初始化  索引 限长  标记 信息
            allocate.clear();
        }
        channel.close();
        channel1.close();

    }
}

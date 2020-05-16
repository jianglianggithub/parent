package com.yibu;

import java.io.*;
import java.nio.ByteBuffer;

public class MappedFile {

    /**
     * MappedByteBuffer 即使没有手动刷盘 也会刷到磁盘 只不过那是需要 os 随机时间去刷盘了。什么时候刷 不清除
     * 传同流式 不会主动刷盘
     * FileChannel 和 MappedByteBuffer 都是写到 PageCache 由os 刷盘
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        ByteBuffer byteBuffer = ByteBuffer.wrap("aaaaaaaaaaaaaaaaaaaaaaaa".getBytes());
        byte[] bytes=new byte[1024];

        byteBuffer.get(bytes,1,10);
        System.out.println();
        byteBuffer.get(bytes,0,10);
        System.out.println();

//        File file = new File("d:/123.txt");
//        RandomAccessFile rw = new RandomAccessFile(file, "rw");
//
//        FileChannel channel = rw.getChannel();
//        ByteBuffer wrap = ByteBuffer.wrap("aaaaa".getBytes());
//
//        System.out.println(channel.position());
//        channel.write(wrap);
//        System.out.println(channel.position());

//        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 1024);
//        map.putInt(97);
//        map.force();

//        FileWriter fileOutputStream = new FileWriter(new File("d:/123.txt"));
//        fileOutputStream.write("aaaa");
//        fileOutputStream.close();
    }
}

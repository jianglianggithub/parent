package com.aa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class A {
    public static void main(String[] args) throws IOException {
//        FileChannel rw = new RandomAccessFile(new File("d:/a.aaa"), "rw").getChannel();
//        MappedByteBuffer map = rw.map(FileChannel.MapMode.READ_WRITE, 0, 1024);
//        MappedByteBuffer map1 = rw.map(FileChannel.MapMode.READ_WRITE, 0, 1024);
//
//        byte[] bytes=null;
//        map.put(bytes,0,10);
//
//        System.out.println(map1.getInt());
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(Math.pow(2,31)-1);
//        String str=null;

        RandomAccessFile rw = new RandomAccessFile(new File("d:/aaaa"), "rw");
        FileChannel channel = rw.getChannel();
        channel.write(ByteBuffer.wrap("aaaaa\r\nbb\tcccc".getBytes()));
        channel.close();
    }
}

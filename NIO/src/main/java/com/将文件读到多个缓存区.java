package com;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.StandardOpenOption;

public class 将文件读到多个缓存区 {
    public static void main(String[] args) throws Exception{
        final FileChannel in = FileChannel.open(new File("d:\\test.txt").toPath(), StandardOpenOption.READ);
        ByteBuffer[] bs={ByteBuffer.allocate(5), ByteBuffer.allocate(10)};
        in.read(bs);
        for (ByteBuffer b : bs) {
            b.flip();
        }
        System.out.println(new String(bs[0].array(),0,bs[0].limit()));

    }
}

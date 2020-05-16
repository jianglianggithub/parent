package com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class NIO_ {
    public static void main(String[] args)throws Exception {
        FileInputStream fileInputStream = new FileInputStream(new File("D:\\sp\\aaa.mp4"));
        FileChannel channel = fileInputStream.getChannel();
        FileOutputStream fileOutputStream = new FileOutputStream(new File("d:\\sss.jpg"));
        FileChannel channel1 = fileOutputStream.getChannel();

        channel.transferTo(0,channel.size(),channel1);

        fileInputStream.close();
        fileOutputStream.close();

        new File("D:\\sp\\aaa.mp4").deleteOnExit();

    }
}

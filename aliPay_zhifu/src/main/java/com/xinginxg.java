package com;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class xinginxg {

    //FileChannel.MapMode.READ_WRITE, 如果这样 那么 即使刷盘后也无法读取 好像只能当前会话读取
    public static void main(String[] args) throws IOException, InterruptedException {
        RandomAccessFile rw = new RandomAccessFile(new File("d:/my.txt"), "rw");

        FileChannel channel = rw.getChannel();
//        MappedByteBuffer map = channel.map(FileChannel.MapMode.PRIVATE, 0, 1024);
        MappedByteBuffer map1 = channel.map(FileChannel.MapMode.READ_WRITE, 0, 2048);

//        map.put("aaa".getBytes());
//        System.out.println("1");
//    map.force();

        byte[] bytes=new byte[3];
        map1.get(bytes);
        System.out.println(new String(bytes));
//
//
//        Thread.sleep(3000000);



    }

    private static void go(int hangshu) {

        for (int j = 1; j <= hangshu; j++) {
            int baiSe = getBaiSe(j, hangshu);
            for (int k = 0; k <baiSe ; k++) {
                System.out.print(" ");
            }
            for (int k = 0; k < getXX(j); k++) {
                System.out.print("*");
            }
            for (int k = 0; k < baiSe; k++) {
                System.out.print(" ");
            }
            System.out.println();
        }

    }



    public static int getBaiSe(int i,int max){
        int xx = getXX(max);
        int xx1 = getXX(i);
        return (xx-xx1)>>>1;
    }

    public static int getXX(int i){
        return 1+((i-1)*2);
    }


}

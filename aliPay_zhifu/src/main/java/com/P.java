package com;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class P {

    public static void main(String[] args) throws IOException {

        File file1 = new File("D:\\1.png");
        RandomAccessFile file = new RandomAccessFile(file1, "r");
        // 1 mb
        long i=1024*10;
        long num= (long) Math.ceil((file1.length()*1.0)/i);
        System.out.println(num);
        System.out.println(file1.length());
        int s=0;
        byte[] bytes=new byte[(int) i];
        for (int j = 0; j < num; j++) {
            RandomAccessFile rw = new RandomAccessFile(new File("d:\\sp\\chunk\\" + j+".svc"), "rw");

                int read = file.read(bytes);
                rw.write(bytes,0,read);

            rw.close();
        }
        file.close();
        System.out.println(s);


        RandomAccessFile randomAccessFile = new RandomAccessFile(new File("d:\\a.jpg"),"rw");
        for (int j = 0; j < num; j++) {
            RandomAccessFile rw = new RandomAccessFile(new File("d:\\sp\\chunk\\" + j+".svc"), "rw");
            int read = rw.read(bytes);
            randomAccessFile.write(bytes,0,read);

        }
        randomAccessFile.close();
    }

}

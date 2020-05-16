package 二维数组;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Timer;
import java.util.TimerTask;

public class A {


    public static void main(String[] args) throws Exception {

        //test#C0A800662C3C18B4AAC260C27C0F0000    303   1469845303
            File file = new File("C:\\Users\\jiangliang\\store\\checkpoint");
        RandomAccessFile rw = new RandomAccessFile(file, "rw");
        FileChannel channel = rw.getChannel();
        int i=("test"+"#"+"-1").hashCode();
        i=Math.abs(i);
        System.out.println(i);
        int slot=i % 5000;
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, file.length());
        //1587305605042 1587305605785
        System.out.println(1587305605785L- 1587305605042L);
        System.out.println(map.getLong(8));
//        int anInt = map.getInt(40 + slot * 4);
//        anInt=40+5000*4+anInt*20;
//        int  a1 = map.getInt(anInt);
//        long a2 = map.getLong(anInt+4);
//        int a3 = map.getInt(anInt+4+8);
//        int a4 = map.getInt(anInt+4+8+4);
//
//        System.out.println(a1);
//        System.out.println(a2);
//        System.out.println(a3);
//        System.out.println(a4);


        channel.close();

    }
}

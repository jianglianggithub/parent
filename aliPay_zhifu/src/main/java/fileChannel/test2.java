package fileChannel;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Scanner;

public class test2 {
    public static void main(String[] args) throws Exception {

        RandomAccessFile rw = new RandomAccessFile(new File("d:/cc.x"), "rw");
        FileChannel channel = rw.getChannel();

        FileLock fileLock = channel.tryLock(0,10,false);
        System.out.println(fileLock);

        ByteBuffer allocate = ByteBuffer.allocate(1024);

        try {
            int read = channel.read(allocate);
            System.out.println(read);
            System.out.println(new java.lang.String(allocate.array(),0,allocate.array().length));
        }catch (Exception e) {
            System.out.println(e.toString());
            Scanner scanner = new Scanner(System.in);
            scanner.hasNext();
        }
        int read = channel.read(allocate);
        System.out.println(read);
        System.out.println(new java.lang.String(allocate.array(),0,allocate.array().length));



    }
}

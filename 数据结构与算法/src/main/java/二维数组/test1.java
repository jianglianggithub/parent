package 二维数组;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class test1 {


    public static void main(String[] args) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        allocate.putInt(1);
        allocate.putInt(2);
        byte[] array1 = allocate.array();

        ByteBuffer allocate1 = ByteBuffer.allocate(20);
        byte[] array = allocate.array();
        allocate1.put(array,0,allocate.position());
    }
}

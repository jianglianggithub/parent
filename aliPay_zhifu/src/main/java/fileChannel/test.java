package fileChannel;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Scanner;
import java.util.concurrent.locks.LockSupport;

/**
 *
 *
 * channel.tryLock(0,10,false);
 * 参数=false = 代表 拿到该 文件的 写锁。
 *
 * 另外一个进程 channel.tryLock(0,10,true); 是可以拿到 读锁的   但是 读锁只能用于 read数据 write直接抛出异常。
 * 但是 在 另外一个进程 拿到 写锁的时候  在没有 realse 之前 是无法 read的。
 *
 *
 *
 *
 *
 *
 */
public class test {

    public static void main(String[] args) throws Exception {
        RandomAccessFile rw = new RandomAccessFile(new File("d:/cc.x"), "rw");
        FileChannel channel = rw.getChannel();
        FileLock fileLock = channel.tryLock(0,10,false);

        channel.write(ByteBuffer.wrap("aaaaa".getBytes()));
        channel.force(false);

        Scanner scanner = new Scanner(System.in);
        scanner.hasNext();

        fileLock.release();
    }
}

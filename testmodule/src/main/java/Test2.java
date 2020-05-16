import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Scanner;
import java.util.concurrent.locks.LockSupport;

public class Test2 {

    public static void main(String[] args) throws Exception {
        RandomAccessFile rw = new RandomAccessFile(new File("d:/bb.x"), "rw");
        FileChannel channel = rw.getChannel();
        FileLock fileLock = channel.tryLock(0,10,false);
        Scanner scanner = new Scanner(System.in);
        scanner.hasNext();

    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Test {
    /* 同一个进程中 获取锁 在未 realse 的时候直接抛出异常  多个进程可以获取 但是未释放的时候 reaed或者 write获取直接gg*/

    /*  当其他进程拿到写锁 本进程在去拿写锁的时候 直接返回null tryLock   lock直接 阻塞 如果为true 可以返回但是不能读写 */
    public static void main(String[] args) throws IOException, InterruptedException {
//        RandomAccessFile rw = new RandomAccessFile(new File("d:/bb.x"), "rw");
//        FileChannel channel = rw.getChannel();
//        new Thread(()->{
//            try {
//                FileLock fileLock = channel.tryLock(0,10,false);
//                System.out.println(fileLock);
//                Thread.sleep(30000);
//            } catch (IOException | InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        }).start();
//        Thread.sleep(1000);
//        FileLock fileLock = channel.tryLock(0,10,false);
//
//        System.out.println(fileLock);
//        channel.write(ByteBuffer.wrap("aaa".getBytes()));
//        LockSupport.park();
    }


    public void aaa(FunctionInterface functionInterface) {

    }
    public void bbb(FunctionInterface functionInterface) {
        /*
        *
        *       本来接收需要一个对象
        *       但是这个接口只有一个方法 那么 如果有一个方法 参数 和这个 函数式接口 的方法 参数是一样的 那么直接 把这个方法引用过来
        *       就相当传了一个 对象 当调用这个 this::bb 传入的对象的 invoker方法就等于调用了 bbb
        * */
    }

    public String bbb(String s) {
        return "";
    }
}

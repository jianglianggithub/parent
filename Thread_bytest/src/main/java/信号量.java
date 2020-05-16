import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class 信号量 {


    public static void main(String[] args) throws InterruptedException {
//        Semaphore semaphore = new Semaphore(5);
//        semaphore.acquire();
//
//        semaphore.release();


//        ScheduledExecutorService asd = Executors
//                .newSingleThreadScheduledExecutor();
//            asd.schedule(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println("11");
//                }
//            },1000,TimeUnit.MILLISECONDS);

        CountDownLatch count = new CountDownLatch(4);
        count.await();
        count.countDown();
//
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        readLock.lock();
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();

        reentrantLock.unlock();
        readLock.unlock();
        System.out.println();
    }
}
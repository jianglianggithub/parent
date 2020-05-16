package mylock;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class App {
    static ReentrantReadWriteLock lock=new ReentrantReadWriteLock(false);
    static ReentrantReadWriteLock.ReadLock  readLock=lock.readLock();
    static ReentrantReadWriteLock.WriteLock writeLock=lock.writeLock();

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                readLock.lock();
                try {
                    Thread.sleep(10000000000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                readLock.unlock();
            }
        }).start();


        Thread.sleep(200);
        writeLock.lock();
        System.out.println("aaaaa");

        writeLock.unlock();
    }
}

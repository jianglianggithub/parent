package zerenlianmos;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class R {

    static ReentrantReadWriteLock lock=new ReentrantReadWriteLock();
    static ReentrantReadWriteLock.ReadLock readLock=lock.readLock();
    static ReentrantReadWriteLock.WriteLock writeLock=lock.writeLock();


    static String str="init";
    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            public void run() {
                readLock.lock();

                try {
                    System.out.println("str value in" + str);
                    Thread.sleep(5800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    readLock.unlock();
                }
            }
        }).start();
        Thread.sleep(100);
        writeLock.lock();

        try {
            str="wirte by";
        }finally {
            writeLock.unlock();
        }

    }
}

package mylock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Run implements Runnable {

     int i=0;

    static final Mylock mylock=new Mylock();
    private static ReentrantReadWriteLock lock=new ReentrantReadWriteLock(true);

    public static void main(String[] args) throws InterruptedException {


        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock.lock();
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                reentrantLock.unlock();
            }
        },"test");
        thread.start();
        Thread.sleep(200);


        reentrantLock.lock();
        condition.signal();
        reentrantLock.unlock();



    }

    @Override
    public void run() {
        mylock.lock();

        for (int j = 0; j <1000; j++) {
            i++;
        }
        mylock.unlock();
    }
}

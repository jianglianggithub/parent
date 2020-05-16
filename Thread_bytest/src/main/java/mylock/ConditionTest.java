package mylock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

    private static ReentrantLock  lock=new ReentrantLock(true);

    private static Condition condition=lock.newCondition();

    public static void main(String[] args) throws Exception{

//        for (int i = 0; i < 10; i++) {
//            Thread thread = new Thread(() -> {
//                lock.lock();
//                try {
//                    condition.await();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }finally {
//                    lock.unlock();
//                }
//            });
//            thread.start();
//            System.out.println();
//        }

        lock.lock();

        condition.await();


        lock.unlock();

    }
}

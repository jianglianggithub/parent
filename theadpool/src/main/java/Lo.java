import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lo {
    private static Lock lock=new ReentrantLock();
    private static Condition condition_product=lock.newCondition();
    private static Condition condition_consumer=lock.newCondition();
    private static List list=new ArrayList();
    private static int length=10;

    public static void main(String[] args) {
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                lock.lock();
                try {
                    while (list.size()>=10){
                        condition_product.await();
                    }
                    list.add(LocalTime.now());
                    System.out.println(" 生产者添加");
                    condition_consumer.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();



        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                lock.lock();
                try {
                    while (list.size()<=0){
                        condition_consumer.await();
                    }
                    System.out.println(list.remove(0)+"   消费者消费");
                    condition_product.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();

        new Thread(()->{

        }).start();
    }



}

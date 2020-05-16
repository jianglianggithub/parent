package mylock;

import javax.swing.plaf.TableHeaderUI;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantLock 只有 独占模式。  但是有 公平 非公平
 *
 *
 * reentrantReadWriteLock 拥有 共享   公平 飞公平
 */
public class LookCode {
   static ReentrantReadWriteLock reentrantReadWriteLock=new ReentrantReadWriteLock(true);
   static ReentrantLock reentrantLock=new ReentrantLock(true);

    /**
     *    1    当 第一个线程  cas 成功 同时  另外一个线程 cas 失败
     *
     *
     *    unlock释放锁 之后unparkSuccessor 方法之所以 需要 从Tail 往 Head方向  查找 Node 主要是 为了怕防止出现 node.next 没有写到主内存
     *    的情况 。。。？ 不愧的大师写的代码。。
     *
     *
     *
     * hasQueuedPredecessors 方法中 主要是 公平锁得情况下，保证 Node 双向链表中 除头节点之后得线程 也就是 先排队 那么优先获取锁
     * 如果后排队 那么  pre.next.thread 就不等于当前线程 只有 是  head 得下一个线程才又资格拿到 锁 ，【当 head  讲 state 设置为0之后。】
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {


        Thread[] threads=new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i]=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        test();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }


        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }

        System.out.println("");




    }


    public static void test() throws InterruptedException {
        reentrantLock.lock();
        reentrantLock.lock();
        Thread thread = Thread.currentThread();
        System.out.println(" 打印咯 "+thread.getName());
        //Thread.sleep(50000000);
        reentrantLock.unlock();
        reentrantLock.unlock();
    }
}

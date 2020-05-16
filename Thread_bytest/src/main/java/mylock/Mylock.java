package mylock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Mylock implements Lock {
    private volatile int temp=0;
    @Override
    public void lock() {
        synchronized (this){

            // 其实这里即使 是 where 也不能保证 一定结果=1w
            //  因为 你只要是 被唤醒之后 都是一个无锁状态 有可能 被唤醒之后  判断 是=0 的  然后执行=1 的过程 中间  执行权 被 其他线程拿到了
            // 那么也会出现 至少 同时有2 个线程在执行 加加操作。
            if (temp==1){
                try {
                    this.wait(); //被唤醒之后 是无锁 状态  有可能 立即 执行器 被抢走 也有可能  在后续 佳佳的时候被抢走。

                    /**
                     *  在被唤醒后      被唤醒的 线程只是 会保留之前执行的 程序计数器 然后 继续执行，但是 记住
                     *  被唤醒之后 线程 是不持有 this 的monitor的。 所以 刚被调度 就有可能 执行权 被其他线程 拿到。
                     *   那么  temp的值  被其他线程 已经 修改=1了 然后  这个线程 又去 执行 加加了。。所以需要 where 。。。
                     */
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            temp=1;
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
       synchronized (this){
           temp=0;
           this.notify();;
       }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}

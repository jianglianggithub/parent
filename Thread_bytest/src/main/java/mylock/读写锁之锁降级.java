package mylock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class 读写锁之锁降级 {

    private static ReentrantReadWriteLock lock=new ReentrantReadWriteLock(true);

    private static ReentrantReadWriteLock.ReadLock readLock=lock.readLock();

    private static ReentrantReadWriteLock.WriteLock writeLock=lock.writeLock();

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    read();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"t1");

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    read();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"t2");

        thread.start();
        thread1.start();

    }


    private static Map map=new HashMap();
    private static Object data=map.get("target");

    private static boolean state=false;

    public static void readWrite() throws InterruptedException {
        readLock.lock();

        if (state){
            readLock.unlock();
            // 在这个期间  线程有可能被切换
            //但是不管怎么切换， 只要 在currentThread  进入 写锁。lock 那么之前的操作对 当前线程都是可见 而且是排他的

            /**
             *  这里有 2种 情况  currentThread 发现状态更改 重新 重 源 读取数据刷新到 data   在获取 写锁的时候
             *   切换了  别的线程 也走到这儿 拿到 写锁 然后  将数据刷新 了， 1. 然后有线程 又拿到写锁修改了值 那么在切回来 state=true  一样能获取最新值
             *                                                                                                                  2. 直接回来 没有再次被 其他线程切换更爱 那么 state=false 就不用在 刷新值了 已经是最新
             */
            writeLock.lock();

            //之所以做这个判断 是因为 有可能  在释放读锁的时候 另外一个 线程 已经 用了 readWrite这个方法 把 data的数据更新 了 那就没必要再次更新了
            if (state){
                data=map.get("target");
                state=false;

            }
            readLock.lock();//释放之前拿到这把读锁  ，  避免 释放写锁线程切换带来  自己读取不到自己刚更新的版本
            writeLock.unlock();
        }

        System.out.println(" 当前 target 的最新值="+data);
        readLock.unlock();

    }


    public static void writer(){
        writeLock.lock();

        map.put("target","1");
        state=true;

        writeLock.unlock();
    }

    public static void read(){
        readLock.lock();

        System.out.println(Thread.currentThread().getName()+"正在获取锁");


        System.out.println(Thread.currentThread().getName()+"正在释放锁");
        readLock.unlock();
    }
}

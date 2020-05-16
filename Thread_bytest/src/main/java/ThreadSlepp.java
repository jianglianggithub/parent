import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by aa on 2019/11/3.
 *  停止线程
 * 1.如果当 需要停止的线程是 睡眠状态的时候 去停止线程的时候  会抛出异常 不会停止 继续执行
 * 2. where 判断
 * 3. 出现异常 继续使用 Thread。currentThread。interrupt
 *
 *
 *
 *
 * Interrrupt()  方法 就相当于 给  Thread种的一个属性 坐了改变     停止不停止 还是得线程 本身 去获取这个值 来坐后续 判断
 *  这个函数起始就是坐一个 标记位。  类似  FutrueChannle  种 添加 监听器 后得  isSuccess isXXX 中去判断  result 一样
 *
 *
 *  当线程 被挂起  阻塞得时候 其实  就相当于 sleep的  所以在 interrupt 是可以唤醒 线程的并抛出异常
 */
public class ThreadSlepp {


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    ArrayBlockingQueue<Object> objects = new ArrayBlockingQueue<Object>(1);
                    objects.put(1);
                    objects.put(1);
                }catch (Exception e){e.printStackTrace();}
            }
        };
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
        Thread.sleep(1000);
        //这儿结果不同 是因为   当 interrupt 之后 其实  在mian线程 等待1s后 子线程已经死亡了，
        // 所以再次判断 虽然 默认不清空 线程 的 终端状态 应该返回true 但是线程  销毁后 其属性会 清除所以返回 false

        // 不sleep 返回 true  然后返回false  是因为 但 使用 thread.interrupt  之后 只是把 线程本地的 属性 的 中断标记 更改了
        // 然后 立即 获取main线程立即获取 子线程的 终端状态 肯定返回 true 这也是 为什么 不sleep  后续的获取 中断值逗是true

        // 但是sleep 之后 却逗是false的原因   sleep 留给了子线程 中断的时间  但是 不sleep 只是更改了标记 那个时候 子线程 并没有 完全死问
        // 只是 更改了 标记  表明改线程 需要中断  至于什么时候 中断 那就是 jvm 中的事情
        //当中断后   线程属性 清除 所以  即使 上面 不清除 clearAttr 也会返回false 是因为 线程 挂了之后 jvm擦除了线程 属性


        //  thread。isInterrupt 只是获取 标记位  相当于线程的一个属性，



        // 也就是说  当线程 阻塞  等待 的时候 会去判断  自己 中断标记 是否 是true 如果是true 那么抛出异常， 间接中断

        //  出现 true fasle 是因为 走到第一个 sout  子线程 还没 等待 或者 阻塞  所以标记 是true  走到第二个之前
        // 子线程 已经 阻塞 等待 状态了 去判断 是否需要中断  然后抛出异常  执行完线程方法体 销毁线程 清除属性了 所以返回fase
        System.out.println(thread.isInterrupted());
        System.out.println(thread.isAlive());
        System.out.println(thread.isInterrupted());


        // 此方法 会返回当前线程的 中断状态 和返回之后  初始化线程的中断状态
        Thread.interrupted();


        /**
         *   线程的6种状态
         *
         *   new  创建
         *   runneale  运行 【包括 运行时 和运行等待，因为线程是被调度中心所调度的。所以在没有抢到cpu的执行权的时候 也算运行】
         *    bolck  阻塞，锁
         *   等待    超时等待
         *    死亡
         */

    }


    @Test
    public void test1() throws InterruptedException {


    }


}

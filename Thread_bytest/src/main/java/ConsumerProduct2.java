import java.util.ArrayList;
import java.util.List;

/**
 * Created by aa on 2019/11/9.
 */
public class ConsumerProduct2 {


    public static void main(String[] args) throws InterruptedException {
        final Store store = new Store();

        new Thread(new Runnable() {
            public void run() {
                for (int i=0;i<100;i++){
                    System.out.println("消费者 get ");

                    try {


                        String s = store.get();
                        System.out.println(s);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                for (int i=0;i<100;i++){
                    System.out.println("生产者  put ");

                    try {

                        store.put();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();




    }
 }


class Store{
    int maxSize=10;
    List<String> list=new ArrayList<String>();

    public synchronized String get() throws InterruptedException {


        if (list.size()==0){
            try {
                System.out.println("消费者释放锁");
                wait();
                System.out.println("消费者 唤醒之后");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        String s = list.get(0);
        list.remove(0);
        System.out.println(" 消费者get到 删除 之后的 list 长度="+list.size());
        notify();
        return s;
    }

    public synchronized void put() throws InterruptedException {

        if (list.size()>=maxSize){
            System.out.println("当前list size 大于最大限长"+list.size()+"   生产者释放锁");
            wait(); // notify 这个方法 其实也只是通知了 jvm虚拟机  有线程释放了 该对象的 monitor 然后直接返回了 有可能在 返回 之后 再次put的时候  jvm还没有去  wait set中把 monitor 分配给其他 线程
                            // 唤醒 其他 wait set中的线程 但是 只是通知 至于什么时候 唤醒 是不能保证的  在其他线程 wait 之时   notify 对应的线程 释放锁 之后
                            // 如果 该 target object 的 monitor 没有被jvm分配给其他线程 那么 改 notify线程  也可以 继续拿到  改对象的 monitor的
                            // 之前的例子是可以证明的
            // notify  ---- 执行完 同步代码块  释放 monitor  ---jvm 把 monitor 分给 wait set中的线程
            //                                                                               --- notify 线程本身 也可以去找jvm拿 自己notify释放的monitor  也就是说 这个地方是公平的
            //                                                                                前提是 在 执行完 同步代码块 之后 和  put 之中这个时间 如果jvm还没选取 出 wait set中的线程分配 monitor  那么  就是公平抢占
        }else {
            System.out.println();
            list.add("产物");
            System.out.println(" 产者put 进去了  put之后的list容量"+list.size());
            notify();
        }

    }




}

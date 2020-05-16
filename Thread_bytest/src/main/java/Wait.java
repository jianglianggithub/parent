import java.util.concurrent.CyclicBarrier;

public class Wait {
    public static Object o=new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                synchronized (o){
                    System.out.println("thread 1");
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("thread 1 第二次打印");
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                synchronized (o){
                    System.out.println("thread 2");
                    o.notifyAll();
                    System.out.println("thread 2    All 之后");
                }
            }
        });

        thread1.start();
        Thread.sleep(2000);
        thread2.start();

        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        cyclicBarrier.reset();
    }




}

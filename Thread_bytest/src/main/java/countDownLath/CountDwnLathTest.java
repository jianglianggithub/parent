package countDownLath;

import java.util.concurrent.CountDownLatch;

/**
 * countDownLacth 是 某个线程  被 唤醒  而 scyibeare 是  达到对应的数量线程阻塞那么就放行
 */
public class CountDwnLathTest implements Runnable {

     static CountDownLatch countDownLatch=new CountDownLatch(20);
    static Thread[] threads=new Thread[20];
    static CountDwnLathTest test=new CountDwnLathTest();
    static {
        for (int i = 0; i < 20; i++) {
            threads[i]=new Thread(test);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        countDownLatch.await();
        System.out.println(test.i);
    }

    @Override
    public void run() {
        synchronized (countDownLatch){
            System.out.println("开始等待 "+Thread.currentThread().getName());

                countDownLatch.countDown();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("开始 执行" +Thread.currentThread().getName());
            go();
        }

    }

    int i=0;
    private void go() {
        i++;

    }


}


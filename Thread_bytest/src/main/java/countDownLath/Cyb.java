package countDownLath;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.LockSupport;

public class Cyb  {

    public static CyclicBarrier cyclicBarrier=new CyclicBarrier(10);

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                LockSupport.park();
                System.out.println("aaa");
            }

        });

        thread.start();
        Thread.sleep(1000);


    }


}

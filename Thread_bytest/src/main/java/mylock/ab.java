package mylock;

import java.util.concurrent.locks.LockSupport;

public class ab {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                LockSupport.park();
            }
        });
        thread.start();
        Thread.sleep(2000);
        System.out.println(thread.getState());
    }
}

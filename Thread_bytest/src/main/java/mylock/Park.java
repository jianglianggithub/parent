package mylock;

import java.util.Scanner;
import java.util.concurrent.locks.LockSupport;

public class Park {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println("1");
                    LockSupport.park();
                }
            }
        });

        thread.start();

        Scanner scanner = new Scanner(System.in);
        scanner.hasNext();

        LockSupport.unpark(thread);

    }
}

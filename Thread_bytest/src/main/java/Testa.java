/**
 * Created by aa on 2019/11/9.
 */
public class Testa {


    public static void main(String[] args) throws InterruptedException {
        final Object o = new Object();

        new Thread(new Runnable() {
            public void run() {
                synchronized (o){
                    System.out.println("aaaaaaa1");
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("aaaaaaa2");
                }
            }
        }).start();

        Thread.sleep(2000);
        new Thread(new Runnable() {
            public void run() {

                while (true){
                    try {
                        Thread.sleep(1100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (o){
                        System.out.println("bbbbbbb1");
                        o.notify();
                        System.out.println("bbbbbbb2");
                    }
                    System.out.println("c");
                }


            }
        }).start();
    }
}

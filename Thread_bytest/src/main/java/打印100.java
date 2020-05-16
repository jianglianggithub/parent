/**
 * Created by aa on 2019/11/10.
 */
public class 打印100 {

    public static Object o=new Object();
    public static volatile int i=0;

    public static void main(String[] args) {

        new Thread(new runable(),"偶数Thread").start();
        new Thread(new runable(),"基数Thread").start();


    }
   static class runable implements Runnable{

        @Override
        public void run() {
            while (i<100){
                synchronized (o){
                    System.out.println(Thread.currentThread().getName()+"    打印"+i++);
                    o.notify();
                    try {
                        if (i != 100){
                            o.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}




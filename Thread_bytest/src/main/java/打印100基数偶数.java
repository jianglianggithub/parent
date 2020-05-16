/**
 * Created by aa on 2019/11/10.
 */
public class 打印100基数偶数 {
    static volatile int i=0;
    static volatile Object o=new Object();
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (i<100){
                    synchronized (o){
                        if ((i & 1)==1){
                            System.out.println(Thread.currentThread().getName()+"     "+i++);
                        }

                    }
                }
            }
        },"基数线程").start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (i<100){
                    synchronized (o){
                        if ((i & 1)==0){
                            System.out.println(Thread.currentThread().getName()+"     "+i++);
                        }
                    }
                }
            }
        },"偶数线程").start();
    }
}

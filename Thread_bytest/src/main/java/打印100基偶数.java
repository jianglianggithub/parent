/**
 * Created by aa on 2019/11/10.
 */
public class 打印100基偶数 {

    public static volatile boolean target=false;
    static volatile int num=0;
    public static void main(String[] args) {
        Object o = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (num<100){
                    synchronized (o){
                        if (num %2 ==0){
                            System.out.println("线程一打印 偶数"+num);
                            num++;
                            try {
                                o.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (num<100){
                    synchronized (o){
                        if (num %2 ==1){
                            System.out.println("线程二打印 基数"+num);
                            num++;
                        }
                        o.notify();
                    }
                }
            }
        }).start();





    }
}

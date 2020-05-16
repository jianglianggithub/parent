/**
 * Created by aa on 2019/11/11.
 */
public class Join {


    public static void main(String[] args) throws InterruptedException {
        Thread mainThread = Thread.currentThread();
        Object o = new Object();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    System.out.println(mainThread.getState());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(" zi Thread invoke over");
            }
        });
        thread.start();
        /**
         *    同步代码块 中 调用 wait  使当前线程处于等待状态
         */
        synchronized (o){
            o.wait();
        }

        System.out.println("main invoke  over");

    }
}

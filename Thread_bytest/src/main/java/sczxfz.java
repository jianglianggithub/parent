import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by aa on 2019/11/13.
 */
public class sczxfz {

    static BlockingQueue queue=new ArrayBlockingQueue(100);
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    for (int i = 0; i < 100; i++) {
                        Object take = queue.take();
                        System.out.println(take);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    for (int i = 0; i < 100; i++) {
                         queue.put("sb");

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}

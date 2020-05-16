/**
 * Created by aa on 2019/11/9.
 */
public class TestMultiThread {

    public static void main(String[] args) {

        final TestMultiThread testMultiThread = new TestMultiThread();

        new Thread(){
            @Override
            public void run() {
                testMultiThread.aaa();
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                testMultiThread.aaa1();
            }
        }.start();
    }

    public synchronized void aaa1(){
        while (true){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());

        }
    }

    public synchronized void aaa(){
        while (true){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

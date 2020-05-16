import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Created by aa on 2019/11/9.
 */
public class RunMy implements Runnable {
    public volatile int num=0;
    static AtomicIntegerFieldUpdater<RunMy> a = AtomicIntegerFieldUpdater.newUpdater(RunMy.class, "num");

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InterruptedException {
        RunMy runMy = new RunMy();

        new Thread(runMy).start();
        new Thread(runMy).start();
        Thread.sleep(1000);
        System.out.println(runMy.num);
        int b=50;
        test(b++);
        System.out.println(b);
    }

    private static void test(int i) {
        System.out.println(i);
    }

    @Override
        public void run() {

                for (int i = 0; i < 10000; i++) {
                    for (;;){
                        int target=num;
                        if (a.compareAndSet(this,target,++target)) {
                            break;
                        }
                    }


                }

    }



}

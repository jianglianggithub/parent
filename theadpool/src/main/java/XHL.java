import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class XHL extends AbstractQueuedSynchronizer {
    volatile int init;

    public XHL(int init){
        setState(init);
        this.init=init;
    }


    @Override
    protected int tryAcquireShared(int arg) {
        for (;;){
            int state = getState();
            if (state-arg>=0){
                if(compareAndSetState(state,state-arg)){
                    return 1;
                }
            }else {
                return -1;
            }
        }
    }


    @Override
    protected boolean tryReleaseShared(int arg) {
        for (;;){
            int state = getState();
            if (state+arg<=init){
                if(compareAndSetState(state,state+arg)){
                    return true;
                }
            }else {
                return false;
            }
        }
    }



    public static void main(String[] args) {
        XHL xhl = new XHL(5);
        ExecutorService executorService = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 20; i++) {
            executorService.execute(()->{
                xhl.acquireShared(1);
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                xhl.releaseShared(1);
            });
        }

    }
}

package mylock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
// 尝试获取共享锁就是获取 读锁    只有读锁是共享的
public class AQS extends AbstractQueuedSynchronizer {



    @Override
    protected boolean tryAcquire(int arg) {

        int state = getState();
        if (state==0 && compareAndSetState(0,arg)){
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }else if (getExclusiveOwnerThread()==Thread.currentThread()){
            setState(getState()+arg);
            return true;
        }
        return false;

    }


    @Override
    protected boolean isHeldExclusively() {
        return Thread.currentThread()==getExclusiveOwnerThread();
    }

    @Override
    protected boolean tryRelease(int arg) {
        if (!isHeldExclusively())
            throw new RuntimeException();

        int state = getState();

        setState(state-arg);
        if (getState()==0) {
            setExclusiveOwnerThread(null);
            return true;
        }

        return false;
    }
}

package shejimos;

import java.util.concurrent.BrokenBarrierException;

public class Filter1 implements Filter {
    @Override
    public void filter(Invoker invoker,String param) {
        System.out.println("第一个过滤器"+param);
    }

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

    }
}

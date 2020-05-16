import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by aa on 2019/11/12.
 */
public class JiaJiaCuowuTongji implements Runnable {
    public int cont=0;
    public boolean[] target=new boolean[100000];
    public AtomicInteger num=new AtomicInteger();
    public CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
    @Override
    public void run() {


        for (int i = 0; i < 10000; i++) {

            cont++;
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            synchronized (this){
                if (target[cont] && target[cont-1]){
                    num.getAndIncrement();
                    System.out.println("出现 错误的 count "+cont);
                }
                target[cont]=true;

            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        JiaJiaCuowuTongji runable = new JiaJiaCuowuTongji();
        new Thread(runable).start();
        new Thread(runable).start();

        Thread.sleep(1100);
        System.out.println(runable.cont);
        System.out.println("出现 错乱的次数 ="+runable.num);
    }
}

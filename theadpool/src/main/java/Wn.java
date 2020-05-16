import org.w3c.dom.ls.LSException;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Wn {

    private static Object monitor=new Object();

    private static List list=new ArrayList();

    private static int length=10;
    public static void main(String[] args) {
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                synchronized (monitor){
                    while (list.size()>=10) {
                        try {
                            monitor.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    monitor.notifyAll();
                    list.add(LocalTime.now());
                    System.out.println("    生产了");
                }
            }
        }).start();



            new Thread(()->{
                for (int i = 0; i < 100; i++) {
                    synchronized (monitor){
                        while (list.size()<=0) {
                            try {
                                monitor.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        monitor.notifyAll();
                        System.out.println(list.remove(0)+"   消费了");
                    }
                }
            }).start();

    }
}

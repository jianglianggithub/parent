import java.util.ArrayList;
import java.util.List;

/**
 * Created by aa on 2019/11/10.
 */
public class ConsumerAndProduct {

//    static List<String> list=new ArrayList();
//
//    public static void main(String[] args) {
//        new Thread(new Runnable() {
//            public void run() {
//                while (true){
//                    synchronized (list){
//                        if (list.size()<=0){
//                            try {
//                                list.wait();
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        String s = list.get(0);
//                        System.out.println(s);
//                        list.remove(0);
//                        list.notify();
//                    }
//            // 不管是 消费者 还是生产者  在末尾逗得notify  因为 不然会出现  线程抢占资源  导致死锁   生产者等待  消费者消费完 等待
//
//
//                }
//
//            }
//        },"consumer Thread ").start();
//
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true){
//                    synchronized (list){
//                        if (list.size()>5){
//                            try {
//                                list.wait();
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        list.add(" ovo ");
//                        list.notify();
//                    }
//                }
//
//            }
//        },"product Thread ").start();
//



//    }
}

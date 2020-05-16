import java.util.ArrayList;
import java.util.List;

public class SczXfzMs {


    static Object o=new Object();
    static List<String> task=new ArrayList<String>();




    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    synchronized (o){
                        if (task.size() >=10){
                            try {
                                o.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        task.add(" 生产者放入 target  ");
                        o.notify();
                    }
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {

                    synchronized (o){
                        if (task.size()<=0){
                            try {
                                o.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        String s = task.get(0);
                        System.out.println("消费  "+s);
                        task.remove(s);
                        o.notify();
                    }

                }
            }
        }).start();







    }










}



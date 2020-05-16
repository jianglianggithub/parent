import java.util.ArrayList;
import java.util.List;

/**
 * Created by aa on 2019/11/9.
 */
public class ConsumerProduct {

    public static void main(String[] args) {
        final Object o = new Object();

        final List<String> list=new ArrayList<String>();
        //product
        new Thread(new Runnable() {
            public void run() {

                    while (true){
                        synchronized (o){
                        if (list.size()==0){
                            try {
                                o.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }{
                            System.out.println(list.get(0)+"  池包子");
                            list.clear();
                            try {
                                o.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }

            }
        }).start();

        // consumer
        new Thread(new Runnable() {
            public void run() {

                    while (true){

                        synchronized (o){

                                list.add("发 包子");
                                o.notify();
                        }
                    }

            }
        }).start();
    }
}

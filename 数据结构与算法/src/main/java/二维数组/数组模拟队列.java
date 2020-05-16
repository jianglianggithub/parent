package 二维数组;

public class 数组模拟队列 {

    private int[] queue=new int[10];
    private int read;
    private int write;
    private int num;
    public synchronized void add(int i) throws InterruptedException {
        while (num>=queue.length){
            wait();
        }
        if (queue.length == write) {
            write=0;
        }
        num++;
        queue[write++]=i;
        notifyAll();
    }


    public synchronized int get() throws InterruptedException {
        while (num <= 0){
            wait();
        }
       if (read==queue.length){
           read=0;
       }
       int i= queue[read];
        queue[read++]=0;
        num--;
        notifyAll();
       return i;

    }


    public static void main(String[] args) throws InterruptedException {
        数组模拟队列 aa = new 数组模拟队列();
        for (int i = 0; i < 11; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        aa.add(finalI);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(aa.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        Thread.sleep(2000);
        for (int i : aa.queue) {
            System.out.print(i);
        }
    }
}

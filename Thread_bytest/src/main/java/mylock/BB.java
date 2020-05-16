package mylock;

public class BB {
   static boolean a=false;
   static boolean b=false;
    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                a=true;
                b=true;
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("a   " +a);
                System.out.println("b   "+b);

            }
        }).start();


    }
}

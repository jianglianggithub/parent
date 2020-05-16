public class Test1 {
        int a;
     int b;
     int c;
     int d;
     int e;
     int f;
     int g;
     int h;

    public static void main(String[] args) {

        for (int i = 0; i < 10000; i++) {
            Test1 test1 = new Test1();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    test1.a=1;
                    test1.b=1;
                    test1.c=1;
                    test1.a=2;
                    test1.d=1;
                    test1.e=1;
                    test1.f=1;
                    test1.g=1;
                    test1.h=1;


                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(test1.a+""+test1.b+""+test1.c+""+test1.d+""+test1.e+""+test1.f+""+test1.g+""+test1.h);
                }
            }).start();
        }

    }
}

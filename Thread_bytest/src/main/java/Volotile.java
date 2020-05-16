public class Volotile {

     int a=3;
     int b=4;
    public  void aa(){
        a=1;
        b=2;
    }


    private void print() {
        System.out.println(a+""+b+""+a);
    }//无原子性
    public static void main(String[] args) {

        for (int i = 0; i < 10000; i++) {
            Volotile volotile = new Volotile();
            new Thread(new Runnable() {
                public void run() {

                    volotile.aa();

                }
            }).start();

            new Thread(new Runnable() {
                public void run() {

                    volotile.print();
                }
            }).start();
        }

        }

    }


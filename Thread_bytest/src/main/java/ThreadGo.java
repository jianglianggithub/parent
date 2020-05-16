public class ThreadGo {
     boolean target;

    public static void main(String[] args) throws InterruptedException {

        ThreadGo threadGo = new ThreadGo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!threadGo.target){

                }
            }
        }).start();


        Thread.sleep(2000);
        threadGo.target=true;
    }
}

/**
 * Created by aa on 2019/11/13.
 */
public class ObjectPublicPre {

   static Object o;
   static Listerner listerner;

    public ObjectPublicPre() throws InterruptedException {
        listerner=new Listerner() {
            @Override
            public void Listerner() {
                System.out.println(o);
            }
        };
        for (int i = 0; i <100000; i++) {
            System.out.print("");
        }
        o=new Object();
    }


    public static void main(String[] args) throws Exception {

        InvokeClass invokeClass = new InvokeClass();
        new Thread(new Runnable() {
            @Override
            public void run() {
                invokeClass.invokeListernerMehotd();
            }
        }).start();

        ObjectPublicPre objectPublicPre1 = new ObjectPublicPre();

    }

    static class InvokeClass{
        void invokeListernerMehotd(){
            if (null != listerner){
                listerner.Listerner();
            }else {
                System.out.println("listener = null");
            }
        }
    }


    interface Listerner{

      void   Listerner();
    }
}

package zerenlianmos;

import java.util.ArrayList;
import java.util.List;


public class Go {


    public static void main(String[] args) {
        List<Filter> list=new ArrayList<>();
        Filter filter = new Filter() {

            @Override
            public void go(Invoker invoker, String i) {
                System.out.println(i+"filter Invoker ing ");
                invoker.invoke(i);
            }
        };
        list.add(filter);
        list.add(filter);
        list.add(filter);
        Invoker go = go(list, new Invoker() {
            @Override
            public void invoke(String str) {
                System.out.println(str);
            }
        });
        go.invoke("aa");
    }

    public static Invoker go(List<Filter> list,Invoker real){


        Invoker temp=real;
        for (int i = list.size()-1; i >=0 ; i--) {
            Filter filter = list.get(i);
            Invoker temp1=temp;
            int finalI = i;
            temp=new Invoker() {
                @Override
                public void invoke(String str) {
                    filter.go(temp1, str);
                }
            };
        }

        return temp;



    }
}

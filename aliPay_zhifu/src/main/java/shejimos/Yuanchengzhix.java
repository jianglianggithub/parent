package shejimos;

public class Yuanchengzhix implements Invoker {
    @Override
    public void go(String param) {
        System.out.println("拿到参数远程执行"+param+"业务逻辑");
    }


    public static void main(String[] args) {
        Invoker target=new Yuanchengzhix();
        Filter[] filters={new Filter1()};

        Invoker temp=target;
        for (int i = 0; i < filters.length; i++) {
            Filter filter = filters[i];
            Invoker temp1=temp;
            temp=new Invoker() {
                @Override
                public void go(String param) {
                    filter.filter(temp1,param);
                }
            };

        }

    }
}

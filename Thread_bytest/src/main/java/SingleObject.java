public class SingleObject {
    private static volatile   SingleObject single;  // 加了 volatile 后 保证 在对 该变量赋值的时候   的所有 汇编指令 操作不被 cpu 指令重排序

    private SingleObject(){}


    public static SingleObject getSingle() {
        if (single==null){
            synchronized (SingleObject.class){
                if (single==null){
                    single=new SingleObject();  //但是这样是不完美的   因为这是3个步骤， sys同步器 能保证   原子性 可见性  但是不能保证 不被cpu指令重排序
                            // 因为  new 对象 是有2个步骤的    1. 创建一个空对象
                    //                                                                   2.然后 执行 构造方法   然后 在 3.赋值   但是有可能 创建对象后 直接就赋值了  那么 构造器中的初始化内容并没有执行
                }
            }

        }

        return single;
    }
}

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by aa on 2019/11/13.
 */
public class A {

    private   volatile int a=0;

    public static Unsafe unsafe;

    static {

        try {
            Field field = null;
            field = LockSupport.class.getDeclaredField("UNSAFE");
            field.setAccessible(true);
             unsafe = (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) throws Exception{
        long a1 = unsafe.objectFieldOffset(A.class.getDeclaredField("a"));
        System.out.println();
        A a = new A();
        unsafe.compareAndSwapInt(a,a1,0,1);
        System.out.println(a.a);
    }
}

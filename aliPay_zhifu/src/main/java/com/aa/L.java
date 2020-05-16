package com.aa;

import java.io.File;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class L {
    public static void main(String[] args) throws Exception {
        RandomAccessFile rw = new RandomAccessFile(new File("d:/1.jpg"), "rw");
        MappedByteBuffer map = rw.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, 1024);
        Method[] methods = map.getClass().getMethods();
        Method method=null;
        for (int i = 0; i < methods.length; i++) {
            if (methods[i].getName().equals("attachment")) {
                method=methods[i];
                break;
            }
        }

        Method finalMethod = method;
        Object o = AccessController.doPrivileged(new PrivilegedAction<Object>() {
            public Object run() {
                try {

                    finalMethod.setAccessible(true);
                    return finalMethod.invoke(map);
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
            }
        });
        System.out.println((L) null);
    }


    public static void aaa(Class... aa){
        System.out.println(aa.length);
    }
}

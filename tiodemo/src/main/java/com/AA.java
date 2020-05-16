package com;

import java.util.ArrayList;
import java.util.List;

public class AA {
    public static void main(String[] args) {
        A a = new A() {
            @Override
            public void b() {


            }
        };
        List<A> list=new ArrayList<>();
        list.add(a);

//        list.forEach(AA::aaaa);
        // 2是找一个方法 来接收这个 函数的 param
        list.forEach(A::b);
        //  方法引用有2层意思 一是 直接调用  A 接口的 b方法  function体中

    }

    private static void aaaa(A a) {

    }


}

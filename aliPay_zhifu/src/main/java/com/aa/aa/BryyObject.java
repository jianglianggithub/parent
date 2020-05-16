package com.aa.aa;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public class BryyObject implements ObjectMy {

    int i=1;

    public static void main(String[] args) {
        AA a = new AA();
        Map map=new HashMap();
        a.setMap(map);

        map.put("aaa","aaaaq1");
        String s = JSON.toJSONString(a);

        AA aa = JSON.parseObject(s, AA.class);
        System.out.println(aa.getMap().getClass());
    }

    public BryyObject() {

        System.out.println("BryyObject instance");
    }


    @Override
    public void getId() {
        System.out.println("id");
    }

    @Override
    public void getName() {
        System.out.println("date"+"     name");
    }

    @Override
    public void relase() {
        --i;
    }

    @Override
    public int getI() {
        return i;
    }
}

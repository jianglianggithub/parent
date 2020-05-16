package com.JSONtest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.Page;

public class Rz extends Parent {
    private String rz;

    public String getRz() {
        return rz;
    }

    public void setRz(String rz) {
        this.rz = rz;
    }

    public static void main(String[] args) {
        SerializerFeature[] features = new SerializerFeature[] {
                SerializerFeature.WriteClassName,
//                SerializerFeature.SkipTransientField,
//                SerializerFeature.DisableCircularReferenceDetect
        };
        Rz rz = new Rz();
        rz.rz="1";
        rz.setName("1");
        rz.setName2("2");
        String string = JSON.toJSONString(rz);
        System.out.println(string);
        Rz parent = JSON.parseObject(string, Rz.class);
        System.out.println(parent.getName());
        System.out.println(parent.getName2());
    }
}

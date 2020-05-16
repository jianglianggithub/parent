package com.aa.aa;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AA {


    public static void main(String[] args) {
        int[] arr={7,2,4,6,1,8};
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length-i; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i]=arr[j];
                    arr[j]=temp;
                }
            }
        }

        Arrays.stream(arr).forEach(item-> System.out.println(item));
    }
   private String aa="aaa";

   private String bb="aaaccc";

   private Map map=new ConcurrentHashMap();

    public String getAa() {
        return aa;
    }

    public void setAa(String aa) {
        this.aa = aa;
    }

    public String getBb() {
        return bb;
    }

    public void setBb(String bb) {
        this.bb = bb;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}

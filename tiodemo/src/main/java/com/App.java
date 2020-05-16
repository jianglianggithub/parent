package com;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) {
        test("IE");
    }



    //平均分配算法
    public static List<Object> allocate(List<String> cls, String curcl, List<Object> qall){

        int index=cls.indexOf(curcl);
        int mod = qall.size() % cls.size();
        int avg =  qall.size() <= cls.size() ? 1 : (mod > 0 && index < mod ? qall.size() / cls.size() + 1 : qall.size() / cls.size());
        int startIndex = ( mod > 0 && index < mod) ? avg * index : avg * index + mod;
        int range = Math.min(avg , qall.size() - startIndex);
        List<Object> rs=new ArrayList<>();
        for (int i = 0; i < range; i++) {
            rs.add(qall.get(startIndex + i));
        }

        return rs;
    }


    public static void aaaa(int maxOneConsumerSize,List<Runnable> store) {

        Executor executor= Executors.newSingleThreadExecutor();
        for (int i = 0; i <store.size();) {
            List<Runnable> runnables=new ArrayList<>();
            for (int j = 0; j < maxOneConsumerSize; j++,i++) {

                if (store.size() <= j)
                    break;
                Runnable runnable = store.get(i + j);
                runnables.add(runnable);
            }

            executor.execute(()->{
                runnables.forEach(Runnable::run);
            });
        }
    }

    public static void test(String str) {
        char old[]="AEHIJLMOSTUVWXYZ12358".toCharArray();
        char temp[]="A3HILJMO2TUVWXYZ1SEZ8".toCharArray();
        int mid=str.length() / 2 + 1 ;
        char[] chars = str.toCharArray();
        boolean test=false;
        for (int i = 0; i < mid; i++) {
            int index = getIndex(old, chars[i]);
            if (index == -1) {
                index = getIndex(temp, chars[i]);
                if (index==-1) {
                    return;
                }
                char dy = old[index];
                if (chars[chars.length - 1 - i] != dy) {
                    return;
                }
            }else {
                char dy = temp[index];
                if (chars[chars.length - 1 - i] != dy) {
                    return;
                }
            }
        }
        System.out.println("是镜像字符串");

    }

    private static int getIndex(char[] chars, char aChar) {
        for (int i = 0; i < chars.length; i++) {
            if (chars[i]==aChar) {
                return i;
            }
        }
        return -1;
    }
}

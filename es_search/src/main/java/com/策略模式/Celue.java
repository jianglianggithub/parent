package com.策略模式;

public interface Celue {

    CelueV1 newCelue(int s);

    interface CelueV1{
        int jisuan(int a,int b);
    }
}

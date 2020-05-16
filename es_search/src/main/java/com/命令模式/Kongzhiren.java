package com.命令模式;

public class Kongzhiren   {


    private MinglingInteface minglingInteface;

    public Kongzhiren(MinglingInteface minglingInteface) {
        this.minglingInteface=minglingInteface;
    }



    public void fachuminglin() {
        minglingInteface.fachuminglin();
    }
}

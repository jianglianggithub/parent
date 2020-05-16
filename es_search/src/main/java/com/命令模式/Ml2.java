package com.命令模式;

public class Ml2 implements MinglingInteface {


    private Caozuo caozuo;
    public Ml2(Caozuo caozuo) {
        this.caozuo=caozuo;
    }

    @Override
    public void fachuminglin() {
        caozuo.guandeng();


    }

}

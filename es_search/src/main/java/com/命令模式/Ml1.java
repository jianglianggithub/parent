package com.命令模式;

public class Ml1 implements MinglingInteface {
    private Caozuo caozuo;

    public Ml1(Caozuo caozuo) {
        this.caozuo=caozuo;
    }


    @Override
    public void fachuminglin() {

        caozuo.kaideng();

    }

}

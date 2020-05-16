package com.策略模式;

public class CelueTe implements Celue {
    @Override
    public CelueV1 newCelue(int s) {
        return s<0 ? new CelueV11() : new CelueV22();
    }


    static class CelueV11 implements CelueV1{

        @Override
        public int jisuan(int a, int b) {
            return a+b;
        }
    }

    static class CelueV22 implements CelueV1{

        @Override
        public int jisuan(int a, int b) {
            return a/b;
        }
    }
}

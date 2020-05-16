package com.aa.aa;

public class WrapperBryyObject implements ObjectMy {

    private  ZZQ.MyRefence myRefence;
    private  ObjectMy bryyobject;

    public WrapperBryyObject(ObjectMy bryyobject, ZZQ.MyRefence myRefence) {
        this.bryyobject=bryyobject;
        this.myRefence=myRefence;
    }

    @Override
    public void getId() {
        bryyobject.getId();
    }

    @Override
    public void getName() {
        bryyobject.getName();
    }

    @Override
    public void relase() {
        bryyobject.relase();
        if (bryyobject.getI()== 0 ) {
            ZZQ.zzq.list.remove(myRefence);
        }
        bryyobject=null;
    }

    @Override
    public int getI() {
        return bryyobject.getI();
    }
}

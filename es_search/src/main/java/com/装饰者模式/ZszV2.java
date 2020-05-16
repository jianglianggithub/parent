package com.装饰者模式;

public class ZszV2 extends AbstractPerson {

    public ZszV2(Person person){
        super(person);
    }

    @Override
    public void active() {
        super.active();
        System.out.println("v2 装饰");
    }


    public static void main(String[] args) {
        ZszV1 zszV1 = new ZszV1(new PersonV1());
        ZszV2 zszV2 = new ZszV2(zszV1);
        zszV2.active();
    }


}

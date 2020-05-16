package com.装饰者模式;

public class ZszV1 extends AbstractPerson {

    public ZszV1(Person person){
        super(person);
    }

    @Override
    public void active() {
        super.active();
        System.out.println("v1 装饰");
    }
}

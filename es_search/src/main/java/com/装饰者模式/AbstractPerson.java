package com.装饰者模式;

public  class AbstractPerson implements Person {

     Person person;

    public  AbstractPerson(Person person) {
        this.person=person;
    }


    @Override
    public void active() {
        person.active();
    }
}

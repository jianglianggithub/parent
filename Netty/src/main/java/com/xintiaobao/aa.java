package com.xintiaobao;

import com.AddressBookProtos;
import com.xx.BOg;

import java.io.IOException;

public class aa {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {
        AddressBookProtos.Person.Builder personBulider = AddressBookProtos.Person.newBuilder();

        AddressBookProtos.Person.PhoneType phoneType = AddressBookProtos.Person.PhoneType.HOME;



        AddressBookProtos.Person.PhoneNumber.Builder phoneNumber = AddressBookProtos.Person.PhoneNumber.newBuilder();
        phoneNumber.setNumber(phoneType);
        personBulider.setPhones(phoneNumber);
        personBulider.setMath("aaa");
        personBulider.setCompute("BB");
        System.out.println(personBulider.build().toString());



    }
}

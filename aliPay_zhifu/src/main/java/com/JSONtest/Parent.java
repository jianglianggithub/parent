package com.JSONtest;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

public class Parent extends Parent2 {
    private String name;

    public void setName(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }
}

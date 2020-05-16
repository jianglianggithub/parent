package com.组合模式;

import java.util.ArrayList;
import java.util.List;

public class Directory implements AbstractDirectory {

    private String name;
    List<AbstractDirectory> list=new ArrayList<>();


    public Directory(String name) {
        this.name=name;
    }
    @Override
    public void add(AbstractDirectory abstractDirectory) {
        list.add(abstractDirectory);
    }

    @Override
    public void delete(AbstractDirectory abstractDirectory) {
        list.remove(abstractDirectory);
    }

    @Override
    public void print() {
        System.out.println(this);
        for (AbstractDirectory directory : list) {
           directory.print();

        }
    }

    @Override
    public String toString() {
        return "文件夹名称 : "+name;
    }
}

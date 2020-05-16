package com.组合模式;

public interface AbstractDirectory {




    void add(AbstractDirectory abstractDirectory);
    void delete(AbstractDirectory abstractDirectory);
    void print();

}

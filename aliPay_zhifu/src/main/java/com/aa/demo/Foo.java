package com.aa.demo;

import org.apache.poi.ss.formula.functions.T;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Foo {

public void one() {
    System.out.println("one");
}
public void two() {
    System.out.println("to");
}
public void three() {
    System.out.println("tree");
}


    public static void main(String[] args) {
        Foo foo = new Foo();
        FutureTask futureTaska = new FutureTask(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());

                foo.one();
            }
        },null);

        FutureTask futureTaskb = new FutureTask(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());

                foo.two();
            }
        },null);
        FutureTask futureTaskc = new FutureTask(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());

                foo.three();
            }
        },null);



        new Thread(new Runnable() {
            @Override
            public void run() {



                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                                futureTaskc.run();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },"c").start();


                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        futureTaska.run();
                    }
                },"a").start();



                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while (futureTaska.isDone()) {
                                futureTaskb.run();
                                break;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },"b").start();





            }
        }).start();
    }
}

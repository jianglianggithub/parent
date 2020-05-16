package com.aa;

public class BBB {


    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("!");
        });
        Thread thread1 = new Thread(() -> {
            System.out.println("2");
        });
        thread.setDaemon(true);
        thread.start();
        thread1.start();




    }
}

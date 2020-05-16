package com.yibu;

import java.util.concurrent.CompletableFuture;

public class BBB {


    public static void main(String[] args) {


        CompletableFuture.supplyAsync(()->1/0).exceptionally(a->{
            if (a instanceof Exception){
                System.out.println("1");
            }
            return 0;
        });
    }
}

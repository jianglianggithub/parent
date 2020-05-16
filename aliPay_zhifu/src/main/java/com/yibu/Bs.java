package com.yibu;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Bs {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        long start = System.currentTimeMillis();
        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "商品详情";
        },executorService);

        CompletableFuture<String> futureB = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "卖家信息";
        },executorService);

        CompletableFuture<String> futureC = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "库存信息";
        },executorService);

        CompletableFuture<String> futureD = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(222000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "aaa";
        }, executorService);

        CompletableFuture<Void> allFuture = CompletableFuture.allOf(futureA, futureB, futureC, futureD);
        allFuture.join(); // allof 等待所有 异步结果完成。  anyOf 只要有一个完成即可

//        System.out.println(futureA.join() + futureB.join() + futureC.join() + futureD.join());
        System.out.println("总耗时:" + (System.currentTimeMillis() - start));
        futureA.thenApply((a)->{
return "1";
        });
    }
}

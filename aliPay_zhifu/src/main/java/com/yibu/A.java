package com.yibu;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class A {



    public void a1(){
        System.out.println("A a1");
        getB();
    }

    public void getB() {
        System.out.println("A getB");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException, Exception {
        CompletableFuture<Object> s = new CompletableFuture<>();
        CompletableFuture<Object> future = s //每当调用 s的任意方法都会新生成要给futre 代表后续的futre 接收前面futre完成task后的result
                .thenApply((a)->{
                    System.out.println("invoke");
                    return "1";
                });

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //当完成第一个futre 后 第一个futre的 handle 什么的都不会执行了。只会执行 后续的了
                // 这个有点像那个  zerenlianmos 轮询调用
                s.complete("3");
            }
        }).start();

        System.out.println(future.get());
    }
}

package com.yibu;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class Futrue1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //提交异步任务 有返回
        //CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "aa");
        //提交异步任务 无返回
        //CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> System.out.println(""));

        //对异步的结果  更改并且消费
        //System.out.println(future.thenApplyAsync(a -> a + "ss").get());
        // 对结果  消费 不能更改
        //System.out.println(future.thenAccept(a-> System.out.println(a)).get());

        // 链接2个异步任务 完成时触发 函数 thenAcceptBoth 结合2个不需要返回
        //String s1 = future.thenCombine(CompletableFuture.supplyAsync(() -> "vvv"), (s11, s2) -> s11 + s2).get();
        //System.out.println(future);

        //thenCompose  返回的是一个新的 future 因为 对第一个 返回值修改 不一定类型的想同的 方便操作
//        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> "1").thenApply((a) -> 1);

        //没有加 Asyn 的方法 其实 也是支持 异步回调的 只不过 没加Asyn 的方法 会 在提交任 处理机制的时候
        // 判断 是否 isDone  如果完成直接同步执行  否则 等待异步回调。 而Asyn的方法 回直接交给线程池执行。
        //在 有容器的环境下 比如web环境 无需 join  或者指定了 自己的 线程池。
        //默认的线程池 在main线程退出后 会退出 应该跟 threadPool的优先级有关。
        CompletableFuture<String> futureA = CompletableFuture.
                supplyAsync(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("1");
                    return     "执行结果:" + (100/0);
                }, Executors.newSingleThreadExecutor()) //提交一个异步有返回的任务
                .thenApply(s -> {
                    return     "apply result:" + s;
                }) // 当异步任务完成后 回调 同步执行 并对结果做出修改
             //如果发生异常的情况下。 如果对异常做了 处理 那么后续 的 handle  when 都不会在传入 异常。
                .whenComplete((a,b)->{
                    System.out.println(a+b);
                })//whenComplete 和 handle 的区别在于 是否  允许有返回值。【syn or  Asyn】
                .handle((s, e) -> {
                    System.out.println(Thread.currentThread());
                    if (e == null) {
                        System.out.println(s);//futureA result: 100
                    } else {
                        System.out.println(e.getMessage());//未执行
                    }
                    return "handle result:" + (s == null ? "500" : s);
                }); //处理 发生异常或者未发生异常。



    }
}

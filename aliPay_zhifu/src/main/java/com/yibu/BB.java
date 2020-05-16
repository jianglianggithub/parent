package com.yibu;

import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BB {


    public static void main(String[] args) throws InterruptedException {
//        CompletableFuture.supplyAsync(()->100/0+"")
//                .whenComplete((r,e)->{
//                    System.out.println("handle   "+ r +e);
//                })
//                .exceptionally(e->{
//                    System.out.println(e);
//                    return "1";
//                }).join();

//        System.out.println(CompletableFuture.supplyAsync(() ->{
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return 100 / 0 + "";})
//                .whenComplete((x,y)->{
//
//                })
//                .join());

    //如果没有对异常做处理 那么 直接抛出来。

        // handle 和 when是不同的 在handle 中 是可以有返回值的  也就是说
        // 如果handle在Excoetion 之前 那么 后面的 异常处理是不会在进行回调 因为 默认你对异常做了处理
        // 但是 whenCompate 其 没有返回值 即使在 excption之前   异常方法也能走。在后面的话 excption做了处理
        // 后续的 whenComplate 害是handle 都  不会在接到异常。

        // 因为 future 需要一个返回值，没有返回值的话 就无法  给futrue 返回 默认值了 出现异常的情况下
        // 所以得要依靠  有返回得、
        //


// 有一个奇怪得现象 就是当自己设置值后    最后一个不会得到执行
        ExecutorService executor = Executors.newSingleThreadExecutor();
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {

            System.out.println("正在执行1");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "1";
        }, executor)
                .thenApply(a->{
                    System.out.println("4");
                    return "aa";})
                .whenComplete((a,b)->{
                    System.out.println("3");
                })
.handle((a,e)->{
    System.out.println("5");
    try {
        Thread.sleep(10000);
    } catch (InterruptedException e1) {
        e1.printStackTrace();
    }
    System.out.println("5-1");
    return "1";
})
                .handle((a,e)->{
            System.out.println("正在执行2");

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            System.out.println(Thread.currentThread());
            System.out.println("end");
            return "1";
        }).whenComplete((a,e)->{
                    System.out.println("end");
                });

        //怎么说呢 就是上面这一大串 都是在构建  result  当你完成了result之后 那么后续得不在走了

        System.out.println(Thread.currentThread());
        //Thread.sleep(1000);
        Scanner scanner = new Scanner(System.in);
        scanner.hasNext();
        scanner.next();
        System.out.println(future.isDone());
        future.complete("aa");
        System.out.println("设置值成功");
        System.out.println(future.isDone());
        System.out.println(future.join());
        System.out.println("设置值成功后获取");

        scanner.hasNext();
        System.out.println(future.join());

        /**
         *
         *  之所以最后一个不走了  在提前 complate的情况下
         *  是因为 你已经把最后一个 futre.complate(x) 定义成了完成 所以最后一个当然不走 了。。
         *
         *
         */

    }
}

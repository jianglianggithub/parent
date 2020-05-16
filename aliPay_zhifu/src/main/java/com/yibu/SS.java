package com.yibu;

import java.util.List;
import java.util.concurrent.*;

public class SS {

    /**
     *
     *
     *
     *    futre.xxxx  和 fure.Asynxxx的最大区别
     *      就是。前者 的唤醒 是由上一个 futre 完成 result 设值的线程 唤醒的。【这也是为什么 同步 竟然是线程池线程执行的】
     *      而后者 是 当执行完 异步任务后。将task 直接 excutor.excutor(task) 了。
     *
     *      也就是 上面的是 run.run() 【这个同步执行的线程也有可能是 线程池的线程】异步的情况
     *      下面是交给 默认的或者自己设置的线程池 去执行【将任务交给 当前线程 所在的线程池去执行】异步的情况
     *
     *
     *      如果没提交异步任务  直接new 出来 那么谁完成 谁执行  当 .xxxx 不是 异步的时候 如果是 交给 默认的线程池执行
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Ex ex = new Ex(executorService);
        // 这里又有一个东西。。只有当 你是 异步提交了任务 后续的 futre 才会用 线程池  回调。如果你是new 出来
        // 手动完成的，那么 当你 complate 的时候 直接 唤醒 后续的 futre，同步。
        CompletableFuture<Integer> f1 =CompletableFuture.supplyAsync(
                ()->{
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return 1;
                }
        );
        //当对 某个 furue 完成的 时候 会直接同步唤醒 下【一级】的非 异步futre。
        f1.complete(2);
       //当提交异步回调时 任务已经完成 isDone了 那么直接 同步执行 除非是 ansyHandle xxx
        // 并且当 手动 complate的时候 也会唤醒后续 的  futre的回调
        CompletableFuture<Object> handle = f1.handleAsync((a, e) -> {
            //也就是说为什么 get 会出现异常 是因为 他在 这个里面做了 超时检测 如果超时 直接 thow了异常 当获取的时候就会报错
            System.out.println(Thread.currentThread().getName());
            throw new RuntimeException();
    });

        CompletableFuture<Integer> handle1 = f1.handle((a, e) -> {
            System.out.println(Thread.currentThread().getName());
            return 1;
        });

        handle1.get();
        System.out.println(f1.get());

    }

    public static class Ex extends AbstractExecutorService {


        private  Executor executor;

        public Ex(Executor executor) {
            this.executor=executor;
        }

        @Override
        public void shutdown() {

        }

        @Override
        public List<Runnable> shutdownNow() {
            return null;
        }

        @Override
        public boolean isShutdown() {
            return false;
        }

        @Override
        public boolean isTerminated() {
            return false;
        }

        @Override
        public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
            return false;
        }

        @Override
        public void execute(Runnable command) {
            System.out.println("ex");
            executor.execute(command);

        }
    }
}

import java.util.concurrent.*;

public class Pool {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        // Callable 其实就是一个 runnble得加强版   会放到  runnbke得run方法中 只不过有返回值
        // 那么 runnble得 git是判断  state
        // 那么callable 就是判断  result  和state 都完成。  Callable就是把 call方法 放到 runnble run里面执行了。 RunnableFuture   是具体实现接口【result 通过Futrue接口得方法获取】
        Future<String> submit = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return null;
            }
        });

        submit.get();
    }
}

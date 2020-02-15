package qu.com.time.qu.com.duoxianchen;


import java.util.Random;
import java.util.concurrent.*;

public class CallableAndFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ExecutorService threadpool = Executors.newSingleThreadExecutor();
//        Future<String> future = threadpool.submit(()->{
//            Thread.sleep(2000);
//            return "hello";
//        });
//        System.out.println("等待结果");
//        System.out.println("拿到的结果"+future.get());
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<>(threadPool);
        for (int i = 0; i <10 ; i++) {
            int finalI = i;
            completionService.submit(() -> {


                Thread.sleep(new Random().nextInt(5000));

                return finalI;
            });
        }
        for (int i = 0; i <10 ; i++) {
            Future<Integer> take = completionService.take();
            System.out.println(take.get());
        }
    }

}

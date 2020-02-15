package qu.com.time.qu.com.duoxianchen;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            int finalI1 = i;
            threadPool.execute(() -> {
                for (int j = 0; j < 10; j++) {
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "is pooling of" + j+"for taks of"+ finalI1);
                }
            });
        }
        System.out.println("all of the task commted");
//        threadPool.shutdown();
//        Executors.newScheduledThreadPool(3).schedule(()->{
//            System.out.println("bombing");
//        },6,TimeUnit.SECONDS);
        Executors.newScheduledThreadPool(3).scheduleAtFixedRate(()->{
            System.out.println("bombing");
       },6,2,TimeUnit.SECONDS);
    }

}

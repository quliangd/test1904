package qu.com.ForkJion;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class TestForkJion {
    /**
     * 自己实现并行流（一起并发来计算，会从中间开始拆分任务，不断的才分任务，知道临界点，cup的利用率高（如果空闲会偷取其他线程任务来执行））
     * 可以多条线程来执行任务，主要根据个人的电脑配置来，看是几核的cup就可以执行几条线程
     */
    @Test
    public void test1(){
        Instant start = Instant.now();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJionCalculate(0,100000000000L);
        Long sum = pool.invoke(task);
        System.out.println("sum = " + sum);
        Instant end = Instant.now();
        System.out.println("Forjion耗费的时间为："+Duration.between(start,end).toMillis());

    }

    /**
     * 使用for循环（顺序方式来计算，只有一条线程执行任务）
     */
    @Test
    public void test2(){
        long sum1 = 0;
        Instant start1 = Instant.now();
        for (long i = 0; i <=100000000000L ; i++) {
            sum1+=i;
        }
        System.out.println("sum1 = " + sum1);
        Instant end1 = Instant.now();
        System.out.println("fori消耗的时间为;" + Duration.between(start1,end1).toMillis());
    }

    @Test
    public void test3(){

        Instant start1 = Instant.now();

        LongStream.rangeClosed(0,100000000000L)
                .parallel()//并行流
//                .sequential()//顺序流
                .reduce(Long::sum);

        Instant end1 = Instant.now();
        System.out.println("消耗的时间为;" + Duration.between(start1,end1).toMillis());
    }
}

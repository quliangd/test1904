package qu.com.ForkJion;

import java.util.concurrent.RecursiveTask;

public class ForkJionCalculate extends RecursiveTask<Long>{

    private long start;
    private long end;

    public ForkJionCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    private static final long THRESHOLD = 10000;
    @Override
    protected Long compute() {
        long sum = 0;
       if(end - start <=THRESHOLD ){
           for (long i = start; i <=end ; i++) {
                 sum+=i;
           }
           return sum;
       }else {
           long midle = (start+end)/2;
           ForkJionCalculate left = new ForkJionCalculate(start,midle);
           left.fork();//拆分，并将该子任务压入线程队列
           ForkJionCalculate right = new ForkJionCalculate(midle + 1, end);
           right.fork();
           return left.join()+right.join();
       }
    }
}

package qu.com.time.qu.com.duoxianchen;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Counter{
    //创建ReentrantLock所对象，也就是初始化锁实例
    //向上转型为Lock接口
    private Lock lock = new ReentrantLock();
    //定义个变量等于0
    private int value = 0 ;

    public void add(int m){
        lock.lock();//此方法一定要在try方法之外，因为获取锁有可能失败，失败之后就不会往下执行
        try {
            this.value+=m;
        } finally {
            //不管什么情况都要释放锁
            lock.unlock();
        }
    }

    public void dec(int m){
        lock.lock();
        try {
            this.value-=m;
        } finally {
            lock.unlock();
        }
    }
    public int get(){
        lock.lock();
        try {
            return this.value;
        } finally {
            lock.unlock();
        }
    }
}

public class ReentrantLocktest {

    final static int LOOP = 100;

    public static void main(String[] args) throws Exception {
        Counter counter = new Counter();
        Thread t1 = new Thread(){
            public void run(){
                for (int i = 0; i <LOOP ; i++) {
                  counter.add(1);
                }
            }
        };
        Thread t2 = new Thread(){
            public void run(){
                for (int i = 0; i <LOOP ; i++) {
                    counter.dec(1);
                }
            }
        };
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(counter.get());
    }


}

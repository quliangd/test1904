package qu.com.time.qu.com.duoxianchen;

import org.junit.Test;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时器的使用
 */
public class TraditionalTimerTest {

    private static int count = 0;

    /**
     * 定时器10秒后爆炸
     */
    @Test
    public void test1() {
        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                System.out.println("bombing!");

            }
        }, 10000);
        while (true) {
            try {
                //打印当前时间
                System.out.println(new Date().getSeconds());
                //每隔1秒
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 定时器10秒后爆炸，然后每个三秒爆炸一次
     */
    @Test
    public void test2() {
        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                System.out.println("bombing!");

            }
        }, 10000, 3000);
        while (true) {
            try {
                //打印当前时间
                System.out.println(new Date().getSeconds());
                //每隔1秒
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     *定时任务，隔2秒执行一次，再每隔5秒执行一次
     * 使用的是递归的思想，开始隔2秒钟执行一次任务，执行完成之后，
     * 因为在MyTimerTask的run方法内，又重新定义了一个定时器，来再次调用润方法，所以run方法在不停的执行
     * 所以是隔2秒执行一次，再每隔5秒执行一次
     */
    @Test
    public void test3() {
        //自定义一个类，继承TimerTask
       class MyTimerTask extends TimerTask{

           @Override
           //实现父类的抽象方法
           public void run() {
               System.out.println("bombing!");
               //再new一个定时器按，隔5秒执行MyTimerTask类中的任务
               new Timer().schedule(new MyTimerTask(),1000);
           }
       }
       //创建定时任务
        new Timer().schedule(new MyTimerTask(), 5000);
        while (true) {
            try {
                //打印当前时间
                System.out.println(new Date().getSeconds());
                //每隔1秒
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     *定时任务，每隔3秒执行一次，再每隔5秒执行一次（定义一个全局变量来count来记录状态，不停在0与1之间切换）
     * 使用的是递归的思想，开始隔2秒钟执行一次任务，执行完成之后，
     * 因为在MyTimerTask的run方法内，又重新定义了一个定时器，来再次调用润方法，所以run方法在不停的执行
     * 所以是隔2秒执行一次，再每隔5秒执行一次
     */
    @Test
    public void test4() {

        //自定义一个类，继承TimerTask
        class MyTimerTask1 extends TimerTask{

            @Override
            //实现父类的抽象方法
            public void run() {
                count = (count+1)%2;
                System.out.println("bombing!");
                //再new一个定时器按，隔5秒执行MyTimerTask类中的任务
                new Timer().schedule(new MyTimerTask1(),3000+2000*count);
            }
        }
        //创建定时任务
        new Timer().schedule(new MyTimerTask1(), 3000);
        while (true) {
            try {
                //打印当前时间
                System.out.println(new Date().getSeconds());
                //每隔1秒
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


package qu.com.time;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestSimpleDateFormat  {
	public static void main(String[] args) throws Exception {
		//日期格式化对象，用来对日期进行格式化
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		//创建一个有10个线程的
		// 线程池，用来多线程执行任务
        ExecutorService pool = Executors.newFixedThreadPool(10);
		//创建多线程要执行的任务
		Callable<Date> task = new Callable<Date>() {
			@Override
			public Date call() throws Exception {
				return sdf.parse("20161218");
			}
		};

		//创建一个集合用来存放多线程执行的任务
		List<Future<Date>> results = new ArrayList<>();
		//用for循环执行10次，调用线程池的10条线程来同时执行
		for (int i = 0; i <10 ; i++) {
			results.add(pool.submit(task));
		}
		//遍历集合
		for (Future<Date> dateFuture : results) {
			System.out.println("dateFuture = " + dateFuture);
			System.out.println(dateFuture.get());
		}
		//关闭线程池
		pool.shutdown();

	}
}

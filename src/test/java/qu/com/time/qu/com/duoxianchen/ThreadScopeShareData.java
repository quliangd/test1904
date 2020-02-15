package qu.com.time.qu.com.duoxianchen;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
public class ThreadScopeShareData {

	private  static Map<Thread,Integer> threadData = new HashMap<>();
//	private static int data =0;
	public static void main(String[] args) {
		for (int i = 0; i <2 ; i++) {
			new Thread(() -> {
				//每个线程拿到的数据时不一样的，线程内拿到的数据时一样的
			  int data = new Random().nextInt();
				System.out.println(Thread.currentThread().getName() + "has put data:" + data);
				threadData.put(Thread.currentThread(),data);
				new A().get();
				new B().get();
			}).start();
		}
	}
	static class A{
		public void get(){
			int data = threadData.get(Thread.currentThread());
			System.out.println("A from:"+Thread.currentThread().getName()+"has put data:"+data);
		}
	}
	
	static class B{
		public void get(){
			int data = threadData.get(Thread.currentThread());
			System.out.println("B from:"+Thread.currentThread().getName()+"has put data:"+data);
		}
	}
}

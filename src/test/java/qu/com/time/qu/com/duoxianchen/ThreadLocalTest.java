package qu.com.time.qu.com.duoxianchen;

import java.util.Random;

public class ThreadLocalTest {
	private static ThreadLocal<MyThreadScopeData> myThreadScopeData= new ThreadLocal<>();
	public static void main(String[] args) {
		for (int i = 0; i <2 ; i++) {
			new Thread(() -> {
				//每个线程拿到的数据时不一样的，线程内拿到的数据时一样的
				int data = new Random().nextInt();
				System.out.println(Thread.currentThread().getName() + "has put data:" + data);
				/*MyThreadScopeData myDate = new MyThreadScopeData();
				myDate.setName("name"+data);
				myDate.setAge(data);
				myThreadScopeData.set(myDate);*/
				//拿到一个与本线程有关的实例对象
				MyThreadScopeData.getThreadInstance().setName("name"+data);
				MyThreadScopeData.getThreadInstance().setAge(data);
				new A().get();
				new B().get();
			}).start();
		}
	}
	static class A{
		public void get(){
//			MyThreadScopeData myDate = myThreadScopeData.get();
//			myDate.getName();
//			myDate.getAge();
			//拿到一个与本线程有关的实例对象
			MyThreadScopeData myDate = MyThreadScopeData.getThreadInstance();
			myDate.getName();
//			myDate.getAge();
			System.out.println("A from:"+Thread.currentThread().getName()+"getMyData:"+myDate.getName()
					+","+myDate.getAge()
			);
		}
	}

	static class B{
		public void get(){
//			MyThreadScopeData myDate = myThreadScopeData.get();
//			myDate.getName();
//			myDate.getAge();
			MyThreadScopeData myDate = MyThreadScopeData.getThreadInstance();
			myDate.getName();
//			myDate.getAge();
			System.out.println("B from:"+Thread.currentThread().getName()+"getMyData:"+myDate.getName()
					+","+myDate.getAge()
			);
		}

	}
}

class MyThreadScopeData{
	private MyThreadScopeData() {}
	private static ThreadLocal<MyThreadScopeData> map = new ThreadLocal<>();
	public static /*synchronized*/ MyThreadScopeData getThreadInstance(){
		MyThreadScopeData instance = map.get();
		if(instance==null){
			instance = new MyThreadScopeData();
			map.set(instance);
		}
		return instance;
	}
	private String name;
	private int age;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}

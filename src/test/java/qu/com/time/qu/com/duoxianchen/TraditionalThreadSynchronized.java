package qu.com.time.qu.com.duoxianchen;


public class TraditionalThreadSynchronized {

    /**
     * @param args
     */
    public static void main(String[] args) {/*
		//静态方法中不能new内部类的实例对象，因为内部类可以访问外部类的成员变量，而访问外部类的成员变量必须要先有外部类的实例对象
		//而main方法是静态的，静态方法执行的时候可以不用创建外部类的对象，如果没有外部类对象就没有外部成员变量，所以此时的内部类
		//就没有外部类的成员变量访问
		Outputer outputer = new Outputer();
		new Thread(()->{
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			outputer.output("zhangxiaoxiang");
		}).start();*/

        new TraditionalThreadSynchronized().init();
    }

    //因为这个方法不是静态方法，所以要调用次方法，一定要有对象才能调用，所以可以将上面的代码拷贝到这个方法里面
    private void init() {
        Outputer outputer = new Outputer();
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                outputer.output("zhangxiaoxiang");
            }

        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                outputer.output2("huoliming");
            }

        }).start();
    }


   static class Outputer {
        //方法上加synchronized是将方法内所有的代码都同步起来，相对来说效率比较低
        public void output(String name) {
            synchronized (Outputer.class) {
                int len = name.length();
//			synchronized (this) {//这个方法是把方法里面一段代码同步
                for (int i = 0; i < len; i++) {
                    System.out.print(name.charAt(i));

                }
                System.out.println();
            }
        }

        public synchronized void output2(String name) {
            int len = name.length();
//			synchronized (this) {//这个方法是把方法里面一段代码同步
            for (int i = 0; i < len; i++) {
                System.out.print(name.charAt(i));

            }
            System.out.println();
        }

        //		}
        public static synchronized void output3(String name) {
            int len = name.length();
//			synchronized (this) {//这个方法是把方法里面一段代码同步
            for (int i = 0; i < len; i++) {
                System.out.print(name.charAt(i));

            }
            System.out.println();
        }
//		}

    }
}

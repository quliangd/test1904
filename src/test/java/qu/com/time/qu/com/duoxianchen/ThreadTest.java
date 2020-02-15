package qu.com.time.qu.com.duoxianchen;


public class ThreadTest{
    public static void main(String[] args) {

        MyThreadA task = new MyThreadA();
        new Thread(task).start();
        new Thread(task).start();
    }

}

class MyThreadA implements Runnable {

    @Override
    public void run() {
         int number=0;
        while (true){
            number++;
            System.out.println(number);
            if(number>50){
                break;
            }
        }
    }
}



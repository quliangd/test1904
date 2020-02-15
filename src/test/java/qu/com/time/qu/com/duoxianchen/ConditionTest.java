package qu.com.time.qu.com.duoxianchen;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class TaskQueue{
    //创建一个LinkedList，向上转型，用父类来接
    final Queue<String> queue = new LinkedList<>();
    //创建锁对象
    final Lock lock = new ReentrantLock();
    //通过锁对象获取条件对象
    final Condition condition = lock.newCondition();


}

public class ConditionTest {
    public static void main(String[] args) {

    }
}

package qu.com.lambda;

public class MyTest1 {

    /**
     * 运用lambda表达式来做运算，lambda表达实际上就是接口实现类的简写
     * 运用lambdab表达式来作为参数传递，实际的功能在lambda表示的方法体中完成
     *
     * @param args
     */
    public static void main(String[] args) {

        Integer num = orpretion(1000, x -> x * x);
        System.out.println("num = " + num);
    }

    /**
     *对传递过来的数值做运算
     * @param num 传递的数值类型
     * @param myFuntion 函数式接口
     * @return
     */
    public static Integer orpretion(Integer num , MyFuntion1 myFuntion){
        return myFuntion.getValue(num);
    }
}

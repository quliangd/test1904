package qu.com.lambda;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

/**
 * 方法引用：若lambda 体中的内容有方法已经实现了，我们可以使用“方法引用”
 *          （可以理解为方法引用是lambda表达是的另一种表现形式）
 *
 *主要有三种语法
 * 对象::实例方法名
 * 类::静态方法名
 * 类::实例方法名
 * 注意事项：
 * ①lambda 体中调用方法的参数列表与返回值类型，要与函数式接口中的抽象方法的函数列和返回值类型保持一致
 * ②若lambda 参数列表中的第一参数是实例方法的调用者，而第二个参数是实例方法的参数式，可以使用ClassName::
 *
 * 构造器引用
 * ClassName::new
 * 注意：需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表一致
 *
 * 数组引用
 * Type::new
 *
 */
public class MyTest04 {



    public static void main(String[] args) {

//        Function<Integer,String[]> function1 = (x)->new String[x];
        Function<Integer,String[]> function1 = String[]::new;

        String[] strs = function1.apply(10);
        System.out.println("strs.length = " + strs.length);

        System.out.println("====================华丽分割线==========================");

        //构造器引用,主要看接口函数中的抽象方法的方法签名要与构造器中的方法签名一致
//        Supplier<Employee> supplier = ()->new Employee();
        Supplier<Employee> supplier2 = Employee::new;
        Employee employee1 = supplier2.get();
        System.out.println("employee1 = " + employee1);
        System.out.println("====================华丽分割线==========================");
//        Function<String,Employee> function = s -> new Employee(s) ;
        Function<String,Employee> function = Employee::new;
        Employee employee2 = function.apply("狗剩");
        System.out.println("employee2 = " + employee2);
        System.out.println("====================华丽分割线==========================");
//        BiFunction<String,Integer,Employee> biFunction = (x,y)->new Employee(x,y);
        BiFunction<String,Integer,Employee> biFunction = Employee::new;
        Employee employee3 = biFunction.apply("陈协", 25);
        System.out.println("employee3 = " + employee3);


        System.out.println("====================华丽分割线==========================");

        //类::实例方法名
        //比较两个字符串是否相等
//        BiPredicate<String,String> biPredicate = (x,y)->x.equals(y);
        //若lambda 参数列表中的第一参数是实例方法的调用者，而第二个参数是实例方法的参数式，可以使用ClassName::method
        BiPredicate<String,String> biPredicate = String::equals;
        boolean test = biPredicate.test("张三", "李四");
        System.out.println("test = " + test);
        System.out.println("====================华丽分割线==========================");

        //类::静态方法名
        Comparator<Integer> com = (x,y)->(x-y)>0?x:y;
        int num = com.compare(8, 6);
        System.out.println("num = " + num);
        System.out.println("====================华丽分割线==========================");
        //两者比较取大值
//        Comparator<Integer> com1 = (x,y)-> Integer.compare(x,y);
        Comparator<Integer> com1 = Integer::compare;
        int num1 = com.compare(8, 6);
        System.out.println("num1 = " + num1);

        System.out.println("compare = " + num);

        System.out.println("====================华丽分割线==========================");
        //对象::实例方法
        Consumer con = s -> System.out.println(s);
        PrintStream ps = System.out;
        Consumer con1 = ps::println;
        Consumer con2 = s->ps.println(s);
        //注意事项：函数式接口的方法签名 Consumer   void accept(T t);
        //必须与lambda方法体中已有方法实现方法的签名一致 PrintStream   void println(Object x)
        Consumer con3 = System.out::println;
        con3.accept("abcdet");
        System.out.println("====================华丽分割线==========================");
        Employee employee = new Employee("张三",18,6000);
        // Supplier<T>接口的方法没有参数，但是有返回值，所以我们一不会用到参数，主要强调方法体中的功能，可以自己自定义实现
//        Supplier<String> supplier = ()->employee.getName();
        Supplier<String> supplier = employee::getName;
        //只能返回String类型的值，因为它是通过上下文推断出来的，
        //因为左边是String类型： Supplier<String> supplier，所以右边也必须为Sting类型
//        Supplier<String> supplier = ()->employee.getAge();// 错误，根据上下文推断（等号左右)，lambda方法体中只能返回String
        String str = supplier.get();
        System.out.println("str = " + str);
        //方法括号都不用写，因为lambda表达式的方法参数要与函数式接口方法参数列表一致，所以可以省略不写；
        Supplier<Integer> supplier1 = employee::getAge;
        Integer integer = supplier1.get();
        System.out.println("integer = " + integer);

    }
}

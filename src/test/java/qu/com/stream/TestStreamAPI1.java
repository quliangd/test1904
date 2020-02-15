package qu.com.stream;

import qu.com.lambda.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 一、 Stream 的操作步骤
 *
 * 1. 创建 Stream
 *
 * 2. 中间操作
 *
 * 3. 终止操作
 */
public class TestStreamAPI1 {

    public static void main(String[] args) {
        //创建Stream
        //1.可以通过Collection系列集合提供的stream()或paralleStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();
        //2.通过Arrays 中的静态方法stream()获取数组流
        Employee[] employees = new Employee[10];
        Stream<Employee> stream2 = Arrays.stream(employees);
        //3.通过Stream类中的静态方法of()
        Stream<String> stream3 = Stream.of("aa", "bb", "cc", "dd");
        //4.创建无限流
        //迭代

        //是一个函数式接口继承了Function<T, T>，传递的两个参数类型为T，T，同一类型：ublic interface UnaryOperator<T> extends Function<T, T>
        //所以返回的类型也应该为同一类型的接口Function的抽象方法参数和返回值类型为同一类型T： R apply(T t);

        Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 2);
        stream4.limit(10).forEach(System.out::println);
        System.out.println("====================华丽分割线==========================");
        //生成
        Stream.generate(()->Math.random()).limit(5).forEach(System.out::println);
    }


}

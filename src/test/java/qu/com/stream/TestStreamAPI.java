package qu.com.stream;

import qu.com.lambda.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TestStreamAPI {

    public static void main(String[] args) {
        /**
         * 1.给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？
         * ，给定【1，2，3，4，5】应该返回【1，4，9，16，25】
         */
        Integer[] nums = new Integer[] {1, 2, 3, 4, 5};
        Arrays.stream(nums).map(x-> x*x).forEach(System.out::println);
        System.out.println("====================华丽分割线==========================");
        /**
         * 2.怎么样用map和reduce方法数一数流中有多少个Employee呢？
         */
        List<Employee> emp = Arrays.asList(
                new Employee("张三", 18, 7000.0, Employee.Status.FREE ),
                new Employee("李四", 20, 6000.0, Employee.Status.BUSY),
                new Employee("王五", 25, 8000.0, Employee.Status.VOCATION),
                new Employee("赵六", 30, 5000.0, Employee.Status.FREE),
                new Employee("田七", 21, 4000.0, Employee.Status.BUSY)
        );
        Optional<Integer> count = emp.stream()
                .map(e -> 1)//用map映射，流中的每个都返回1，也就是有5个1在流中
                .reduce(Integer::sum);//再将流中的5个1求和
        System.out.println(count.get());

    }

}

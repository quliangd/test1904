package qu.com.stream;


import qu.com.lambda.Employee;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 终止操作
 */
public class TestStreamAPI3 {
    /*
        查找与匹配
		allMatch——检查是否匹配所有元素
		anyMatch——检查是否至少匹配一个元素
		noneMatch——检查是否没有匹配的元素
		findFirst——返回第一个元素
		findAny——返回当前流中的任意元素
		count——返回流中元素的总个数
		max——返回流中最大值
		min——返回流中最小值
	 */

    public static void main(String[] args) {

        List<Employee> emp = Arrays.asList(
                new Employee("张三", 18, 7000.0, Employee.Status.FREE ),
                new Employee("李四", 20, 6000.0, Employee.Status.BUSY),
                new Employee("王五", 25, 8000.0, Employee.Status.VOCATION),
                new Employee("赵六", 30, 5000.0, Employee.Status.FREE),
                new Employee("田七", 21, 4000.0, Employee.Status.BUSY)
        );

        //按照boolean值分区
        Map<Boolean, List<Employee>> map1 = emp.stream().collect(Collectors.partitioningBy(e -> e.getPrice() > 6000));
        List<Employee> employees = map1.get(true);
        for (Employee employee : employees) {
            System.out.println("employee = " + employee);
        }
        System.out.println("====================华丽分割线==========================");

        //按照状态进行分组
        Map<Employee.Status, List<Employee>> map = emp.stream().collect(Collectors.groupingBy(Employee::getStatus));
//        List<Employee> employees = map.get(Employee.Status.BUSY);
//        employees.forEach(System.out::println);
        System.out.println("map = " + map);
        System.out.println("====================华丽分割线==========================");
        //获取工资的最大值
        Optional<Employee> max1 = emp.stream().collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getPrice)));
        System.out.println("max1.get() = " + max1.get().getPrice());
        System.out.println("====================华丽分割线==========================");
        //获取工资的总和
        Double sum4 = emp.stream().collect(Collectors.summingDouble(Employee::getPrice));
        System.out.println("sum4 = " + sum4);
        System.out.println("====================华丽分割线==========================");
        //工资的平均值
        Double price = emp.stream().collect(Collectors.averagingDouble(Employee::getPrice));
        System.out.println("price = " + price);
        System.out.println("====================华丽分割线==========================");
        //总数
        Long sum3 = emp.stream().collect(Collectors.counting());
        System.out.println("sum3 = " + sum3);
        System.out.println("====================华丽分割线==========================");
        //collect——将流转换为其他形式。接收一个 Collector接口的实现，用于给Stream中元素做汇总的方法
        //需求：将集emp集合中人的名字收集到一个HashSet集合中
        HashSet<String> hashSet = emp.stream().map(Employee::getName).collect(Collectors.toCollection(HashSet::new));
        hashSet.forEach(System.out::println);
        System.out.println("====================华丽分割线==========================");
        //需求：将集emp集合中人的名字收集到一个set集合中
        Set<String> set = emp.stream().map(Employee::getName).collect(Collectors.toSet());
        set.forEach(System.out::println);
        System.out.println("====================华丽分割线==========================");
        //需求：将集emp集合中人的名字收集到一个list集合中
        List<String> list1 = emp.stream()
                .map(Employee::getName) //获取集合中所有员工的名字
                .collect(Collectors.toList());//使用Collectors工具类来获取一个Collector容器的实例类，list集合。
       set.forEach(System.out::println);
        System.out.println("====================华丽分割线==========================");
        // 终止操作
	    /*
		归约
		reduce(T identity, BinaryOperator) / reduce(BinaryOperator) ——可以将流中元素反复结合起来，得到一个值。

	    */

	    //需求：获取所有员工的工资并求和
        //先获取流中所有员工的公司，在使用reduce方法求和
        Optional<Double> op1 = emp.stream().map(Employee::getPrice).reduce(Double::sum);
        System.out.println("op1.get() = " + op1.get());

        System.out.println("====================华丽分割线==========================");
	    //需求：将集合中的元素累加求和
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        //1、先将集合转换成流，再调用流中的归约方法reduce完成
        Integer sum = list.stream().reduce(0, (x, y) -> x + y);
        System.out.println("sum = " + sum);

        System.out.println("====================华丽分割线==========================");

        boolean b = emp.stream().allMatch(employee -> employee.getStatus().equals(Employee.Status.FREE));
        System.out.println("b = " + b);
        System.out.println("====================华丽分割线==========================");
        boolean b1 = emp.stream().anyMatch(e -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println("b1 = " + b1);
        System.out.println("====================华丽分割线==========================");
        boolean b2 = emp.stream().noneMatch(employee -> employee.getStatus().equals(Employee.Status.BUSY));
        System.out.println("b2 = " + b2);
        System.out.println("====================华丽分割线==========================");
        Optional<Employee> op = emp.stream().sorted((e1, e2)-> -Double.compare(e1.getPrice(),e2.getPrice()))
                .findFirst();
        System.out.println("op.get() = " + op.get());
        System.out.println("====================华丽分割线==========================");

        Optional<Employee> any = emp.stream().filter(employee -> employee.getStatus().equals(Employee.Status.FREE))

                .findAny();
        System.out.println("any.get() = " + any.get());
        System.out.println("====================华丽分割线==========================");
        long count = emp.stream().filter(employee -> employee.getAge()>20)
                .filter(employee ->employee.getName().startsWith("田")).count();
        System.out.println("count = " + count);
        System.out.println("====================华丽分割线==========================");
        Optional<Employee> max = emp.stream().max(Comparator.comparingDouble(Employee::getPrice));
        System.out.println("max.get() = " + max.get());
        System.out.println("====================华丽分割线==========================");
        Optional<Employee> min = emp.stream().min((emp1, emp2) -> -Double.compare(emp1.getAge(), emp2.getAge()));
        System.out.println("min.get() = " + min.get());
        System.out.println("====================华丽分割线==========================");
        //获取最少工资
        Optional<Double> min1 = emp.stream().map(Employee::getPrice).min(Double::compare);
        System.out.println("min1 = " + min1.get());
    }
}

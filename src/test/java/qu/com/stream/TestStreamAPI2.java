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
public class TestStreamAPI2 {

    public static void main(String[] args) {
        //中间操作
        List<Employee> emp = Arrays.asList(
                new Employee("张三", 18, 7000.0),
                new Employee("李四", 40, 6000.0),
                new Employee("王五", 25, 8000.0),
                new Employee("赵六", 25, 8000.0),
                new Employee("田七", 25, 8000.0)
        );

        /*
		映射
		map——接收 Lambda ， 将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
		flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
	    */

        /*
		sorted()——自然排序
		sorted(Comparator com)——定制排序
	    */
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd");
        list.stream().sorted().forEach(System.out::println);
        System.out.println("====================华丽分割线==========================");
        emp.stream().sorted((emp1,emp2)->{
            if(emp1.getAge()==emp2.getAge()){
                return emp1.getName().compareTo(emp2.getName());
            }else {
                return Integer.compare(emp1.getAge(),emp2.getAge());
            }

    }).forEach(System.out::println);

        System.out.println("====================华丽分割线==========================");
        //获取员工的名字
        emp.stream().map(Employee::getName).forEach(System.out::println);
        System.out.println("====================华丽分割线==========================");
        //将字符串转换成大写
        list.stream().map(s->s.toUpperCase()).forEach(System.out::println);
        //因为Function函数中的抽象方法是传入一个类型的参数返回另一个形式的参数，
        //而TestStreamAPI2的filterCharacter方法也是传入一个类型的参数返回另一个类型的参数，所以能接收 TestStreamAPI2::filterCharacter
        //因为list.stream().map返回的是新流，而estStreamAPI2::filterCharacter返回的也是一个字符类型新流
        //所以返回的类型为Stream<Stream<Character>>
        list.stream().map(TestStreamAPI2::filterCharacter) //{{aaa},{bbb},{ccc},{ddd}}
                .forEach(sm->{
                    //遍历出来的流还要继续遍历之后才是字符，最后打印
                    sm.forEach(System.out::println);
                });
        System.out.println("====================华丽分割线==========================");
        //flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
        Stream<Character> stream4 = list.stream().flatMap(TestStreamAPI2::filterCharacter);//{aaa,bbb,ccc,ddd}
        stream4.forEach(System.out::println);

        System.out.println("====================华丽分割线==========================");

        Stream<Employee> stream  = emp.stream().filter(s ->{
            System.out.println("Stream API的中间操作");
            return s.getAge() > 35;} );
        //终止操作
        stream.forEach(System.out::println);
        System.out.println("====================华丽分割线==========================");
        emp.stream().filter(employee ->{
            System.out.println("短路");
            return employee.getPrice()>5000;
        } ).limit(2).forEach(System.out::println);
        System.out.println("====================华丽分割线==========================");
        emp.stream().filter(e->e.getPrice()>5000).skip(2).forEach(System.out::println);
        System.out.println("====================华丽分割线==========================");
        emp.stream().distinct().forEach(System.out::println);

    }
    //定义一个方法，将一个字符串转换成一个一个字符存储到流中
    public static Stream<Character> filterCharacter(String str){
        //创建一个集合用来存储从字符串中遍历出来的字符
        List<Character> list = new ArrayList<>();
        //遍历字符串，将字符串中的每一个字符添加到集合中
        for (char ch : str.toCharArray()) {
            list.add(ch);
        }
        //返回装满字符的流
        return list.stream();
    }

}

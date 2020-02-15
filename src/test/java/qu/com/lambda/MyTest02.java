package qu.com.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MyTest02 {

    public static void main(String[] args) {
        List<Employee> emp = Arrays.asList(
                new Employee("张三", 18, 7000.0),
                new Employee("李四", 20, 6000.0),
                new Employee("王五", 25, 8000.0),
                new Employee("赵六", 23, 5000.0),
                new Employee("田七", 21, 4000.0)
        );
        Collections.sort(emp,(x1,x2)->{
            if(x1.getAge()==x2.getAge()){
                return x2.getName().compareTo(x1.getName());
            }else {
                return Integer.compare(x2.getAge(),x1.getAge());
            }
        });
        for (Employee employee : emp) {
            System.out.println(employee);
        }
        System.out.println("==========================================");

        String string = strHandler("\t\t\t我大黑马", x -> x.trim());
        System.out.println("string = " + string);
        System.out.println("==========================================");

        String string1 = strHandler("dadfasdfas", x -> x.toUpperCase());
        System.out.println("string1 = " + string1);
        System.out.println("==========================================");
        String string2 = strHandler("我的大黑马", x -> x.substring(3, 5));
        System.out.println("string2 = " + string2);

        System.out.println("==========================================");
        //调用方法时，函数式接口中lanbda表达式中的参数可以根据调用的方法泛型来推断
        op(100L,200L,(x,y)->x+y);

        System.out.println("==========================================");
        op(100l,200l,(x,y)->x*y);


    }
    //用于处理字符串
    public static String strHandler(String str,MyFuntion2 myFuntion){
        return myFuntion.getValue(str);
    }
    //需求：对于两个long类型的数据进行处理
    //传入两个类型的参数，函数是接口中就要接收两个相同类型的参数
    //函数式接口上定义泛型，只需要定义泛型的类型即可，不需要指定变量名,传递过来的参数类型一定要与接口中定义的泛型类型一致
    public static void op(Long l1,Long l2 ,MyFuntion3<Long,Long> mf){
        System.out.println(mf.getValue(l1, l2));
    }

}

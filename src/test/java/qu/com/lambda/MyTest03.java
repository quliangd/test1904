package qu.com.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class MyTest03 {
    public static void main(String[] args) {
        //lambda表达式方法体中主要做的事情就是随机产生一个100一类的整数
        List<Integer> numList = getNumList(10, () -> (int) (Math.random() * 100));
        for (Integer integer : numList) {
            System.out.println("integer = " + integer);
        }
        System.out.println("====================华丽分割线==========================");

        //lambda表达式方法体中主要做的事情就将传递过来的字符串变成大写
        String str = strHandler("abced", s -> s.toUpperCase());
        System.out.println("str = " + str);

        System.out.println("====================华丽分割线==========================");
        List<String> strings = Arrays.asList("Hello", "atguigu", "Lambda", "www", "ok");
        //lambda表达式方法体中主要做的事情就是将字符串长度小于等于3的字符串添加到集合中
        List<String> filter = filter(strings, s -> s.length() <= 3);
        for (String s : filter) {
            System.out.println("s = " + s);
        }


    }

    //需求：随机产生10个100以内的整数
    //Supplier<T>供给型接口
    public static List<Integer> getNumList(int num, Supplier<Integer> supplier) {
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            //实现类的方法体中是水机产生一个整数，后面在将整数加入到集合中
            Integer integer = supplier.get();
            integers.add(integer);
        }
        return integers;
    }
    //需求：用于处理字符串
    //Function<T,R>函数型接口
    public static String strHandler(String str, Function<String,String> function){
         str = function.apply(str);
        return str;
    }

    //需求：将满足条件的字符串放入到一个集合中
    //Predicate<T> 断言型接口
    public static List<String> filter(List<String> list,Predicate<String> predicate){
        List<String> strs = new ArrayList<>();
        for (String str : list) {
            if(predicate.test(str)){
                strs.add(str);
            }
        }
        return strs;
    }

}

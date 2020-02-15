package qu.com.Optional;

import java.util.Optional;

import org.junit.Test;
import org.mockito.internal.matchers.Null;
import qu.com.lambda.Employee;

/*
 * 一、Optional 容器类：用于尽量避免空指针异常
 * 	Optional.of(T t) : 创建一个 Optional 实例
 * 	Optional.empty() : 创建一个空的 Optional 实例
 * 	Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例
 * 	isPresent() : 判断是否包含值
 * 	orElse(T t) :  如果调用对象包含值，返回该值，否则返回t
 * 	orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回 s 获取的值
 * 	map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
 * 	flatMap(Function mapper):与 map 类似，要求返回值必须是Optional
 */
public class TestOptional {

    //Optional.of(T t) : 创建一个 Optional 实例
    @Test
    public void test01() {
        Optional<Employee> op = Optional.of(new Employee());
        System.out.println("op = " + op.get());

    }

    @Test
    //	Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例
    public void test02() {
        Optional<Employee> op3 = Optional.ofNullable(new Employee());
        if (op3.isPresent()) {
            System.out.println(op3.get());
        }


    }

    @Test
    public void test03() {
        //Optional.empty() : 创建一个空的 Optional 实例
        //可以构建一个空的Optioal实例，但是没有值，取不到值，如果要取值，就会报空指针异常
        Optional<Employee> empty = Optional.empty();
        System.out.println("empty = " + empty.get());
    }

    //map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
    @Test
    public void test04() {
        Optional<Employee> op4 = Optional.of(new Employee("张三", 18, 5000, Employee.Status.FREE));
        //map方法的返回值类型必须是Optional，但是lambda表达式的的箭头后面的方法体可以是任意类型
//		Optional<String> s1 = op4.map(s->s.getName());
//		System.out.println("op4 = " + op4.get());
        Optional<String> o = op4.flatMap(e -> Optional.of(e.getName()));
        System.out.println("o = " + o.get());

    }

    @Test
    public void tets05() {
        Man man = new Man();
        String godnessName = getGodnessName(man);
        System.out.println(godnessName);
        System.out.println("====================华丽分割线==========================");
//        Optional<NewMan> newMan = Optional.ofNullable(null);
        Optional<NewMan> newMan = Optional.of(new NewMan(Optional.of(new Godness("波多老师"))));
        String godnessName1 = getGodnessName1(newMan);
        System.out.println("godnessName1 = " + godnessName1);
    }


    //需求：获取男神心中的女神的名字
    public String getGodnessName1(Optional<NewMan> newMan) {
        return newMan.orElse(new NewMan(Optional.of(new Godness("苍老师")))).getGodness().get().getName();
    }
    //需求：获取男神心中的女神的名字
    public String getGodnessName(Man man) {
        if (null != man) {
            Godness god = man.getGod();
            if (null != god) {
                String name = god.getName();
                return name;
            }
        }
            return null;
    }
}

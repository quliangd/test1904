package qu.com.lambda;

@FunctionalInterface
interface MyFuntion1 {

    public Integer getValue(Integer num);


}

@FunctionalInterface
interface MyFuntion2 {
    public String getValue(String str);

}

@FunctionalInterface
interface MyFuntion3<T,R> {
    public R getValue(T t1, T t2);

}

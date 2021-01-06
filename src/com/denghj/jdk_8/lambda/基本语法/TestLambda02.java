package com.denghj.jdk_8.lambda.基本语法;

import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

/**
 * lambda表达式的基础语法，java8中引入一个操作符"->" 箭头操作符或者称之为lambda操作符
 * “->” 将lambda表达式拆分成2部分，左侧、右侧，
 * 左侧---》方法的参数列表
 * 右侧--》lambda表达式中所要执行的功能模块，lambda体
 * 如同01示例中，左侧为IEmpStrategy抽象方法中的入参，右侧为其具体实现，但是这个仅针对接口中只有一个抽象方法的使用，
 * 问题：若是接口中有多个抽象方法，那么lambda默认使用那个呢----》详见 lambda函数式接口---》即接口中只有一个抽象方法
 * 注意：
 *      lamda表达式需要函数式接口的支持,函数式接口-》接口钟只有一个抽象方法就是函数式接口，可以使用注解 @FunctionalInterface修饰，修饰之后，接口必须只有一个抽象方法
 *      lambda表达式在使用外部局部变量的使用只能使用final变量，jdk7之前在声明变量时final修饰词必须带上，但是在8之后可以省略，但是内部使用时还是不可修改
 * 语法格式一：
 *   ①、无参、无返回值  ()->System.out.println("hello world");
 * 语法格式二:
 *   ②、一个参数且无返回值的方法 (x)-> System.out.println(x); 若只有一个参数，()可以省略 x-> System.out.println(x);
 * 语法格式三：
 *   ③、2个参数及以上且有返回值的方法，同时lambda体钟有多条语句，则多条语句要放在{}钟包裹起来
 *    Comparator<Integer> comparator = (x,y)->{
 *             System.out.println("x==:"+x); //第一条语句
 *             return Integer.compare(x,y);//第二条语句
 *         };
 * 语法格式四：
 *     ④、2个参数及以上且有返回值的方法，同时lambda体只有一条语句，return 和 {} 都可以省略
 *      Comparator<Integer> comparator1=(x,y)->Integer.compare(x,y);
 * 语法格式五：
 *     ⑤、Lambda表达式的参数列表的数据类型可以省略不写，因为jvm编译器可以通过上下文推断其对应的数据类型，称为“类型推断”
 *      Comparator<Integer> comparator2=(Integer x,Integer y)->Integer.compare(x,y);
 *
 * 总结：
 *   上联：左右遇一括号省
 *   下联：左侧推断类型省
 *   横批：能省则省
 */
public class TestLambda02 {

    /**
     * 语法格式一测试，无参、无返回值
     */
    @Test
    public void test1(){
        int num=0;
        //找一个无参无返回值的函数式接口，如下：
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("线程执行。。。。"+num);
            }
        };
        runnable.run();
        //上面代码现在使用lambda表达式修改
        Runnable runnable1 = () -> System.out.println("hello Lambda");
        runnable1.run();
    }

    /**
     * 语法格式二
     */
    @Test
    public void test2(){
        //有一参无返回值的lambda表达式
        Consumer<String> consumer = (x)-> System.out.println(x);
        consumer.accept("邓怀俊");

    }
    /**
     *语法格式三、四、五
     */
    @Test
    public void test3(){
        //2个参数及以上且有返回值的方法，同时lambda体钟有多条语句,lambda体必须有{}
        Comparator<Integer> comparator = (x,y)->{
            System.out.println("x==:"+x); //第一条语句
            return Integer.compare(x,y);//第二条语句
        };
        //2个参数及以上且有返回值的方法，同时lambda体只有一条语句，return 和 {} 都可以省略
        Comparator<Integer> comparator1=(x,y)->Integer.compare(x,y);
        // ⑤、Lambda表达式的参数列表的数据类型可以省略不写，因为jvm编译器可以通过上下文推断其对应的数据类型，称为“类型推断”
        Comparator<Integer> comparator2=(Integer x,Integer y)->Integer.compare(x,y);

    }
    /**
     * 类型推断示例
     */
    @Test
    public void test4(){
        String[] str={"a","b"};
        List<String> list = new ArrayList<>();
        Map<String,Integer> map = new HashMap<>();
    }

    //运用lambda表达式
    //需求：对一个数进行运算，定义一个函数式接口 MyFunction
    public Integer getVal(int num,IMyFunction myFunction){
        return myFunction.getVal(num);
    }
    @Test
    public void testCal(){
        Integer val = getVal(100, x -> x + 11);
        System.out.println(val);
    }
}

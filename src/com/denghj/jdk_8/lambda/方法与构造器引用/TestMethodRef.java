package com.denghj.jdk_8.lambda.方法与构造器引用;

import com.denghj.jdk_8.lambda.基本语法.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 一、方法引用：若 lambda体中的内容已经有方法实现了，那么我们可以使用“方法引用” 即 lambda表达式的另外一种表达方式
 *
 * 方法引用的三种语法格式：
 *  对象::实例方法名
 *  类::静态方法名
 *  类::实例方法名
 *
 *  使用注意前条件：
 *  lambda方法引用的参数类型及返回值和函数式接口中抽象方法的参数列表和返回值要一致
 *
 *      类::实例方法名  使用条件
 *      使用条件（lambda参数有俩个，第一个参数是lambda体的调用者，第二个参数是lambda体的参数时，这种情况是可以使用类名：：实例方法名）
 *
 *  二、构造器引用
 *  使用条件是：构造器中的参数列表要与函数式接口中抽象方法的参数列表保持一致
 *
 *  ClassName::new
 *  三、数组引用
 *  Type::new
 */
public class TestMethodRef {

    //1、对象::实例方法名 举例test1，test2
    @Test
    public void test1(){
        Consumer<String> consumer = x-> System.out.println(x);
        //使用方法引用之后,前提条件是：方法引用的参数类型及返回值和接口种抽象方法的参数列表和返回值要一致
        Consumer<String> consumer1=System.out::println;
    }
    @Test
    public void test2(){

        Supplier<String> supplier=()->new Employee().getName();
        //使用方法引用改为
        Supplier<String> supplier1=new Employee()::getName;
        String s = supplier.get();
        System.out.println(s);
    }


    //2、类::静态方法名 eg: test3

    @Test
    public void test3(){
        Comparator<Integer> comparator =(x,y)->Integer.compare(x,y);
        Comparator<Integer> comparator1=Integer::compare;
    }
    //3、类::实例方法名  使用条件（lambda参数有俩个，第一个参数是lambda体的调用者，第二个参数是lambda体的参数时，这种情况是可以使用类名：：实例方法名）
    @Test
    public void test4(){
        BiPredicate<String,String> biPredicate=(x,y)->x.equals(y);
        BiPredicate<String,String> biPredicate1=String::equals;
    }
    //构造器引用 (无参构造器)
    @Test
    public void test5(){
        Supplier<Employee> supplier = ()->new Employee();
        //改为构造器引用
        Supplier<Employee> supplier1=Employee::new;
    }
    //一个参数构造器
    @Test
    public void test6(){
        Function<String,Employee> function=(x) -> new Employee(x);
        Function<String,Employee> function1=Employee::new;
        Employee employee = function1.apply("邓怀俊");
        System.out.println(employee.getName());
        Function<Employee,String> function2=employee1 -> employee1.getName();
        Function<Employee ,String> function3=Employee::getName;

    }
    //数组引用
    @Test
    public void test7(){
        Function<Integer,String[]> function = x->new String[x];
        //转换
        Function<Integer,String[]> function1=String[]::new;
        //集合引用
        Supplier<List<Integer>> supplier = ()->new ArrayList<>();
        Supplier<List<Integer>> supplier1= ArrayList::new;
    }
}

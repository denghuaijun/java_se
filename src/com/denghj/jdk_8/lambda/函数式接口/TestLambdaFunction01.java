package com.denghj.jdk_8.lambda.函数式接口;

import com.denghj.jdk_8.lambda.基本语法.Employee;
import com.denghj.jdk_8.lambda.基本语法.TestLambda01;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 由于通过lambda基本语法的学习，发现每次在使用lambda表达式的时候都要先定义一个函数式接口，比较麻烦，所以jdk8新特性钟引入了封装好的
 * 内置四大核心函数式接口
 * Consumer<T>:消费型接口--- void accept(T t); 有参无返回
 * Supplier<T>:供给型接口---- T get();无参有返回
 * Function<T,R>:函数型接口 --- R apply(T t);出入参都有泛型的函数接口
 * Predicate<T>：断言型接口 ---boolean test(T t); 作为判断型接口函数
 */
public class TestLambdaFunction01 {
   static List<Employee> employees = Arrays.asList(
            new Employee("张三",12,1000.12),
            new Employee("李四",22,2000.12),
            new Employee("王五",32,3000.12),
            new Employee("赵六",42,4000.12),
            new Employee("田七",10,5000.12)
    );
    //consumer示例
    public void consum(double money, Consumer<Double> consumer){
        consumer.accept(money);
    }
    @Test
    public void test(){
        consum(1000,money-> System.out.println("消费"+money));
    }

    //供给型接口，获取数据
    public Employee getEmployee(List<Employee> employees, Supplier<String> supplier){
        for (Employee employee:employees){
            String name = supplier.get();
            if (name.equals(employee.getName())){
                return employee;
            }
        }
        return null;
    }
    @Test
    public void testSup(){
        Employee employee = getEmployee(employees, () -> "李四");
        System.out.println(employee);
    }
    //函数型接口
    public Integer testFun(String a, Function<String,Integer> function){
        Integer apply = function.apply(a);
        return apply;
    }
    @Test
    public void testFun(){
        Integer integer = testFun(" ad ", x -> x.length());
        System.out.println(integer);
    }
    //断言型接口
    //判断字符串长度是否为3,如果是则返回字符串，或者返回"you fail"
    public String testStrLen3(String str, Predicate<String> predicate){
        if (predicate.test(str)){
            return str;
        }else {
            return "you fail";
        }
    }
    @Test
    public void testPred(){
        System.out.println(testStrLen3("abc",s -> s.length() == 3));
    }
}

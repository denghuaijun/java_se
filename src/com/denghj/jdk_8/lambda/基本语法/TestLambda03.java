package com.denghj.jdk_8.lambda.基本语法;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * lambda练习
 */
public class TestLambda03 {
    List<Employee> employees = Arrays.asList(
            new Employee("张三",12,1000.12),
            new Employee("李四",22,2000.12),
            new Employee("王五",32,3000.12),
            new Employee("赵六",42,4000.12),
            new Employee("田七",10,5000.12)
    );

    @Test
    public void test1(){
        Collections.sort(employees,(e1,e2)->{
            if (e1.getAge() == e2.getAge()){
                return e1.getName().compareTo(e2.getName());
            }else {
                //return Integer.compare(e1.getAge(),e2.getAge());//升序
                return -Integer.compare(e1.getAge(),e2.getAge());//降序
            }
        });
        employees.forEach(System.out::println);
    }

    //使用IMyFunction2作为参数，将一个字符串转换成大写，并返回
    public String convert(String s,IMyFunction2 myFunction2){
        return myFunction2.getValue(s);
    }
    @Test
    public void convertTest(){
       //使用IMyFunction2作为参数，将一个字符串转换成大写，并返回
        String ret = convert("abcd", s -> s.toUpperCase());
        System.out.println(ret);
        //将一个字符串的第2个和第4个索引位置进行截取
        String abcdef = convert("abcdef", s -> s.substring(2, 4));
        System.out.println(abcdef);
    }
    //需求声明一个带俩个泛型的函数式接口IMyFunction3，泛型类型为<T,R> T为参数 R为返回值，接口钟声明对应抽象方法
    //使用接口作为参数，计算俩个long的和和乘积
    public Long calculate(Long l1,Long l2,IMyFunction3<Long,Long> myFunction3){
        return myFunction3.cal(l1,l2);
    }
    @Test
    public void calTest(){
        Long ret = calculate(100L,200L,(x,y)->x+y);
        System.out.println(ret);
    }



    //通过以上可知，每次在使用lambda表达式的时候都要定义一个函数式接口，那么在后期jdk为开发者提供了已经定义好的函数式接口（四大内置核心的函数式接口）
}

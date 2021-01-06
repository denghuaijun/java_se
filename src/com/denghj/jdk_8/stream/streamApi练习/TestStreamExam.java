package com.denghj.jdk_8.stream.streamApi练习;

import com.denghj.jdk_8.lambda.基本语法.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * streamApI练习题
 */
public class TestStreamExam {
        /*
    	  	1.	给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？
    		，给定【1，2，3，4，5】， 应该返回【1，4，9，16，25】。
    	 */
    @Test
    public void test01(){
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        list.stream().map(x->x*x).forEach(System.out::println);
        //乘积
        Integer integer = list.stream().reduce((x,y) -> x * y).get();
        System.out.println(integer);
    }
    /*
	 2.	怎样用 map 和 reduce 方法数一数流中有多少个Employee呢？
	 */
    List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 59, 6666.66, Employee.Status.BUSY),
            new Employee(101, "张三", 18, 9999.99, Employee.Status.FREE),
            new Employee(103, "王五", 28, 3333.33, Employee.Status.VOCATION),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.BUSY),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.FREE),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.FREE),
            new Employee(105, "田七", 38, 5555.55, Employee.Status.BUSY)
    );
    @Test
    public void test(){
        Optional<Integer> reduce = emps.stream().map(e -> 1).reduce(Integer::sum);
        System.out.println(reduce.get());
    }
}

package com.denghj.jdk_8.stream.stream终止操作;

import com.denghj.jdk_8.lambda.基本语法.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * stream的终止操作
 * 查找与匹配
 * allMatch---查找是否匹配所有元素
 * anyMatch---检查是否至少匹配一个元素
 * noneMatch--是否没有匹配所有元素
 * findFirst--返回第一个元素
 * findAny---返回任意一个元素
 * count---返回元素的总个数
 * max---返回流中的最大数
 * min---返回流中的最小数
 */
public class TestStreamApi03 {

    static List<Employee> employees = Arrays.asList(
            new Employee("张三",12,1000.12, Employee.Status.BUSY),
            new Employee("李四",22,2000.12, Employee.Status.FREE),
            new Employee("王五",32,3000.12, Employee.Status.BUSY),
            new Employee("赵六",42,4000.12, Employee.Status.FREE),
            new Employee("田七",10,5000.12, Employee.Status.VOCATION)
    );

    @Test
    public void test(){
        //allMatch
        boolean b = employees.stream().allMatch(employee -> employee.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b);
        //anyMatch
        boolean b1 = employees.stream().anyMatch(employee -> employee.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b1);
        //noneMatch
        boolean noneMatch = employees.stream().noneMatch(employee -> employee.getName().equals("等坏话就"));
        System.out.println(noneMatch);
        //Findfirst
        Optional<Employee> optionalEmployee = employees.stream().sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())).findFirst();
        System.out.println(optionalEmployee.get());
        //optionalEmployee.orElse(new Employee()); 如果对象为空则，放到另一个对象呢
        //findAny
        Optional<Employee> optionalEmployee2 = employees.stream().filter(e->e.getStatus().equals(Employee.Status.FREE)).findAny();
        System.out.println(optionalEmployee2.get());
        //count
        long count = employees.stream().count();
        System.out.println(count);
        //max
        Optional<Employee> max = employees.stream().max((e1, e2) -> Integer.compare(e1.getAge(), e2.getAge()));
        System.out.println(max.get());
        //min
        Optional<Employee> min = employees.stream().min((e1, e2) -> Integer.compare(e1.getAge(), e2.getAge()));
        System.out.println(min.get());
    }

}

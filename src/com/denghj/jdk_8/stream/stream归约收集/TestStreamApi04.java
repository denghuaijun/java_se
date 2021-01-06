package com.denghj.jdk_8.stream.stream归约收集;

import com.denghj.jdk_8.lambda.基本语法.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 归约：reduce(T identity,BinaryOperator)/reduce(BinaryOperator)--可以将流中的元素反复结合起来得到一个值
 *
 * 收集：collect-将流转换为其它的形式或者对象，接收一个Collector接口的实现，用于给stream中的元素作汇总
 */
public class TestStreamApi04 {
    static List<Employee> employees = Arrays.asList(
            new Employee("张三",12,1000.12, Employee.Status.BUSY),
            new Employee("李四",22,2000.12, Employee.Status.FREE),
            new Employee("王五",32,3000.12, Employee.Status.BUSY),
            new Employee("赵六",42,4000.12, Employee.Status.FREE),
            new Employee("赵六",42,4000.12, Employee.Status.FREE),
            new Employee("田七",10,5000.12, Employee.Status.VOCATION)
    );

    //归约：reduce(T identity,BinaryOperator)/reduce(BinaryOperator)--可以将流中的元素反复结合起来得到一个值
    @Test
    public void test(){
        //reduce(T identity,BinaryOperator)
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer reduce = list.stream().reduce(0, (x, y) -> x + y);
        System.out.println(reduce);//55得出所有集合的sum
        //计算员工中工资的综合reduce(BinaryOperator) 这个有可能为空，所以返回的optional，map进行流数据的提取，
        Double aDouble = employees.stream().map(Employee::getSalary).reduce(Double::sum).get();
        System.out.println(aDouble);
    }


    //收集
    @Test
    public void testCollect(){
        //收集所有员工姓名，并存放在集合中
        List<String> collect = employees.stream().map(Employee::getName).collect(Collectors.toList());
        System.out.println(collect);
        //收集set去重
        Set<String> collect1 = employees.stream().map(Employee::getName).collect(Collectors.toSet());
        System.out.println(collect1);
        //自定义指定集合
        LinkedHashSet<String> collect2 = employees.stream().map(Employee::getName).collect(Collectors.toCollection(LinkedHashSet::new));
        //获取总数
        Long collect3 = employees.stream().collect(Collectors.counting());
        //获取员工薪资的平均值 c4与c5等价
        Double collect4 = employees.stream().map(Employee::getSalary).collect(Collectors.averagingDouble(Double::doubleValue));
        Double collect5 = employees.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(collect4+"----"+collect5);
        //分组
        Map<Employee.Status, List<Employee>> collect6 = employees.stream().collect(Collectors.groupingBy(Employee::getStatus));
        collect6.forEach((k,v)-> System.out.println("状态k："+k+"----对应的数据：v=:"+v));
        //薪资总和
        Double collect7 = employees.stream().collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(collect7);
        //最大值/最小值
        Optional<Employee> collect8 = employees.stream().collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        //根据状态分组统计
        Map<Employee.Status, Long> collect9 = employees.stream().collect(Collectors.groupingBy(Employee::getStatus, Collectors.counting()));
        //多级分组
        Map<Employee.Status, Map<String, List<Employee>>> collect10 = employees.stream().collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy(e -> {
            if (e.getAge() < 35) {
                return "青年";
            } else {
                return "老年";
            }
        })));
        System.out.println(collect10);
        //分片/分区(同上可以单分，也可以多分)
        Map<Boolean, List<Employee>> booleanListMap = employees.stream().collect(Collectors.partitioningBy(employee -> employee.getSalary() > 3000));
       //其它收集
        double sum = employees.stream().collect(Collectors.summarizingDouble(Employee::getSalary)).getSum();
        System.out.println(sum);
        //连接所有员工的姓名 连接符及首尾拼接符
        String collect11 = employees.stream().map(Employee::getName).collect(Collectors.joining(",","",""));
        System.out.println(collect11);//张三,李四,王五,赵六,赵六,田七
    }
}

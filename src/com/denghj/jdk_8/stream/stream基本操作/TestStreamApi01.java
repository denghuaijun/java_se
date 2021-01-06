package com.denghj.jdk_8.stream.stream基本操作;

import com.denghj.jdk_8.lambda.基本语法.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 一、Stream 三大操作步骤：
 * 1. 创建stream
 * 2.中间操作：即流水线操作  将数据源（集合、数组）的数据经过一系列的中间操作
 * 3.终止操作：获取stream流所持有的结果
 */
public class TestStreamApi01 {

    //1、测试  创建stream对象 共四种方式
    @Test
    public void createStream(){
        //方式1、通过Collection中的stream()方法创建
        List<Employee> employeeList = new ArrayList<>();
        //employeeList.parallelStream()创建并行流，默认为串行流
        Stream<Employee> stream = employeeList.stream();
        //方式2、通过Stream api中的静态方法of()
        Stream<String> stringStream = Stream.of("a", "b", "c");
        stringStream.forEach(System.out::println);
        //方式3、通过Arrays中的静态方法stream()获取数组流
        Stream<Employee> employeeStream = Arrays.stream(new Employee[10]);
        //方式4、创建无限流
        //无限流1：无限迭代
        Stream<Integer> iterate = Stream.iterate(0, x -> x + 2);
        //无线流2：产生数据
        Stream<Double> generate = Stream.generate(Math::random);
    }
}

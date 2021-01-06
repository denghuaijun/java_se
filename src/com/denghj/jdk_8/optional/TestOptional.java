package com.denghj.jdk_8.optional;

import com.denghj.jdk_8.lambda.基本语法.Employee;
import org.junit.Test;

import java.util.Optional;

/**
 * 测试optional容器类的常用方法
 *
 * Optional.of(T t)  :创建一个Optional实例
 * Optional.empty()  :创建一个空的Optional实例
 * Optional.ofNullable(T t):若t 不为null;创建Optional实例，或者创建个空的
 * isPresent():判断是否包含值
 * orElse(T t):如果调用对象包含值，返回该值，或者返回t
 * orElseGet(Supplier s):如果调用对象包含值，返回该值，否则返回s获取的值
 * map(Function f): 如果有值对其处理，并返回处理后的optional，否则返回Optional.empty();
 * flatMap(Function mapper): 与map类似，要求返回值必须是Optional
 */
public class TestOptional {
    @Test
    public void test(){
        Employee employee = new Employee();
        //创建optional实例
        Optional<Employee> optionalEmployee = Optional.of(employee);
        Employee emp = optionalEmployee.get();
        System.out.println(emp);
        //通过null来定位空指针异常
        Optional<Object> o = Optional.of(null);//会报空指针，快速定位发生空指针异常的位置
        //构造空Optional实例
        Optional<Employee> empty = Optional.empty();
        //创建一个空对象实例
        Optional<Employee> optional = Optional.ofNullable(null);
    }
}

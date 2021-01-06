package com.denghj.jdk_8.lambda.基本语法;

import org.junit.Test;

import java.util.*;

/**
 * lambda就是一种匿名函数，可以传递的一段代码块，这样提高整体代码执行速率，是代码更紧凑简洁
 */
public class TestLambda01  {

    @Test
    public void test(){

        //匿名内部类jdk7写法
        Comparator<Integer> comparator=new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };
        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
        treeSet.add(6);
        treeSet.add(3);
        treeSet.add(4);
        treeSet.forEach(System.out::println);

        //JDK8使用lambda表达式使用
        Comparator<String> stringComparator = (x,y)->Integer.compare(x.length(),y.length());
        Comparator<Integer> integerComparator = (x,y)->Integer.compare(x,y);
        TreeSet<Integer> set = new TreeSet<>((x,y)->Integer.compare(x,y));
    }

    List<Employee> employees = Arrays.asList(
            new Employee("张三",12,1000.12),
            new Employee("李四",22,2000.12),
            new Employee("王五",32,3000.12),
            new Employee("赵六",42,4000.12),
            new Employee("田七",52,5000.12)
    );

    //需求一：获取员工信息列表中年龄大于20的员工信息
    //------------------------------------传统方法---------------------------------
    //1、传统方法,封装一个方法
    public List<Employee> filterEmployeeByAge(List<Employee> list){
        List<Employee> employeeList = new ArrayList<>();
        for (Employee e:list) {
            if (e.getAge()>20){
                employeeList.add(e);
            }
        }
        return employeeList;
    }
    //测试方法
    @Test
    public void testEmp1(){
        List<Employee> employeeList = filterEmployeeByAge(employees);
        for (Employee e: employeeList) {
            System.out.println(e);
        }
    }
    //需求二：查询薪资大于3000的员工信息
//1、传统方法,封装一个方法
    public List<Employee> filterEmployeeBySal(List<Employee> list){
        List<Employee> employeeList = new ArrayList<>();
        for (Employee e:list) {
            if (e.getSalary()>3000){
                employeeList.add(e);
            }
        }
        return employeeList;
    }
    @Test
    public void testEmp2(){
        List<Employee> employeeList = filterEmployeeBySal(employees);
        for (Employee e: employeeList) {
            System.out.println(e);
        }
    }

    //---------------------------传统方法------------------------
    //-----------------------------优化方式策略模式-----------------------------------------------
    //由以上传统方法可知代码出现大量的冗余，而且改动的关键位置只是条件，所以使用设计模式：策略模式进行修改
    //策略模式，定义一个接口IEmpStrategy，抽离公共的方法，不同条件策略重写实现此方法
    //重载上面的传统方法
    public List<Employee> filterEmployee(List<Employee> list,IEmpStrategy<Employee> empStrategy){
        List<Employee> employeeList = new ArrayList<>();
        for (Employee e:list) {
            if (empStrategy.compare(e)){
                employeeList.add(e);
            }
        }
        return employeeList;
    }
    //
    @Test
    public void testStrategy(){
        List<Employee> employeeList = filterEmployee(employees, new GetEmpByAge());
        employeeList.forEach(System.out::println);
        //将策略模式改为匿名内部类
        List<Employee> employeeList1 = filterEmployee(employees, new IEmpStrategy<Employee>() {
            @Override
            public boolean compare(Employee employee) {
                return employee.getName().equals("张三");
            }
        });
        employeeList1.forEach(System.out::println);
    }
    //-----------------------------优化方式策略模式-----------------------------------------------
    //--------------------------在策略模式的基础上进行Lambda表达式优化------------------------
    @Test
    public void testLambda(){
        List<Employee> employeeList = filterEmployee(employees, e -> e.getAge() > 20);
        employeeList.forEach(System.out::println);
    }
    //---------------使用stream API进行过滤查询---------------------------
    @Test
    public void testStream(){
        employees.stream()
                .filter(employee -> employee.getName().equals("田七"))
                .forEach(employee -> {
                    System.out.println(employee.getName());
                });
                //.forEach(System.out::println);
        employees.stream().map(employee -> employee.getName()).forEach(System.out::println);
    }
}

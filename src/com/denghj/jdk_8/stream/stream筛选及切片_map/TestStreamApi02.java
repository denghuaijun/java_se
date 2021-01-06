package com.denghj.jdk_8.stream.stream筛选及切片_map;

import com.denghj.jdk_8.lambda.基本语法.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * stream的筛选及切片操作
 * filter-接收lambda。从流中过滤一些元素
 * limit-截断流，使其元素不超过指定的数量
 * skip(n)-跳过元素，返回一个扔掉前n个元素的流，若流中的元素不足n个，则返回一个空流，与limit的互补
 * distinct-筛选，通过流所生成的元素的hashcode()和equals的方法去重
 * map-(映射)。接收lambda,将元素转换成其它形式或提取信息，接收一个函数作为参数，该函数会被用到每个元素上，并将其映射到一个新的元素
 * flatMap-(映射)，接收一个函数作为参数，并将流中的每个值换成另一个流，然后把所有的流连成一个流
 * //分页：
 * List<AccessAreaVo> areaVo = vos.stream()
 *     .skip(pageable.getPageSize() * (pageable.getPageNumber() - 1))
 *     .limit(pageable.getPageSize()).collect(Collectors.toList());
 */
public class TestStreamApi02 {
    static List<Employee> employees = Arrays.asList(
            new Employee("张三",12,1000.12),
            new Employee("李四",22,2000.12),
            new Employee("王五",32,3000.12),
            new Employee("赵六",42,4000.12),
            new Employee("田七",10,5000.12),
            new Employee("田七",10,5000.12)
    );

    @Test
    public void test(){
        //创建流
        Stream<Employee> stream = employees.stream();
        stream.filter(employee -> {
            System.out.println("stream流的中间API操作");
            return employee.getAge()>30;
        }).forEach(System.out::println);

        System.out.println("-=====================================");
        //limit;
        employees.stream().filter(employee -> {
            System.out.println("stream流短路");
            return employee.getAge()>30;
        }).limit(2).forEach(System.out::println);
       //skip
        System.out.println("-=====================================");
        //limit;
        employees.stream().filter(employee -> {
            System.out.println("stream流短路");
            return employee.getAge()>30;
        }).skip(1).forEach(System.out::println);
        System.out.println("-=================去重====================");
        //distinct
        employees.stream().distinct().forEach(System.out::println);
    }
    /**
     *  * map-(映射)。接收lambda,将元素转换成其它形式或提取信息，接收一个函数作为参数，该函数会被用到每个元素上，并将其映射到一个新的元素
     *  * flatMap-(映射)，接收一个函数作为参数，并将流中的每个值换成另一个流，然后把所有的流连成一个流
     */
    @Test
    public void test2(){
        //map
        employees.stream().map(Employee::getName).forEach(System.out::println);
        //map 及flatM
        List<String> list =Arrays.asList("aaa","bbb","ccc");
        //使用map来处理多个流，进行遍历
      /*  Stream<Stream<Character>> streamStream = list.stream().map(s -> getCharacterStream(s));
        streamStream.forEach(characterStream -> {
            characterStream.forEach(System.out::println);
        });*/
        //上面方式同样可以使用flatMap进行扁平切分
        list.stream().flatMap(s -> getCharacterStream(s))
                .forEach(System.out::println);

    }
    //将字符串分解为字符数组并转换为对应的集合流
    public Stream<Character> getCharacterStream(String s){
        List<Character> characterList = new ArrayList<>();
        for (Character character : s.toCharArray()){
            characterList.add(character);
        }
        return characterList.stream();
    }
    /**
     * 排序
     * sorted()----自然排序,Comparable
     * sorted(Comparator com)---定制排序
     */
    @Test
    public void test3(){
        List<String> list = Arrays.asList("cc","aa","bb","dd");
        //自然排序
        list.stream().sorted().forEach(System.out::println);
        System.out.println("------------------定制排序如下-------------");
        employees.stream().sorted((x,y)->{
            if (x.getAge() == y.getAge()){
                return x.getName().compareTo(y.getName());
            }else {
                return Integer.compare(x.getAge(),y.getAge());
                //return -Integer.compare(x.getAge(),y.getAge());
            }
        }).forEach(System.out::println);
    }
}


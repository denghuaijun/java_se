package com.denghj.jdk_8.stream.stream归约收集;

import com.denghj.jdk_8.lambda.基本语法.Employee;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 归约：reduce(T identity,BinaryOperator)/reduce(BinaryOperator)--可以将流中的元素反复结合起来得到一个值
 *
 * 收集：collect-将流转换为其它的形式或者对象，接收一个Collector接口的实现，用于给stream中的元素作汇总
 */
public class TestStreamApi04 {
    static Map<String,Employee> map= new HashMap<>();
    static List<Map<String,Object>> mapList= new ArrayList<>();
    static List<Employee> employees = Arrays.asList(
            new Employee("张三",12,1000.12, Employee.Status.BUSY),
            new Employee("李四",22,2000.12, Employee.Status.FREE),
            new Employee("王五",32,3000.12, Employee.Status.BUSY),
            new Employee("赵六",42,4000.12, Employee.Status.FREE),
            new Employee("赵六",42,4000.12, Employee.Status.FREE),
            new Employee("田七",10,5000.12, Employee.Status.VOCATION)
    );

    static {
        Map<String,Object> map1= new HashMap<>();
        map1.put("name","张三");
        map1.put("age",12);
        map1.put("salary",1000);
        Map<String,Object> map2= new HashMap<>();
        map2.put("name","张三1");
        map2.put("age",12);
        map2.put("salary",10000);
        Map<String,Object> map3= new HashMap<>();
        map3.put("name","张三12");
        map3.put("age",22);
        map3.put("salary",324);
        Map<String,Object> map4= new HashMap<>();
        map4.put("name","张三11");
        map4.put("age",32);
        map4.put("salary",500);

//        map1.put("张三",new Employee("张三",12,1000.12, Employee.Status.BUSY));
//        map2.put("张三",new Employee("张三",12,1000.12, Employee.Status.BUSY));
//        map3.put("张三11",new Employee("张三11",22,1000.12, Employee.Status.BUSY));
//        map4.put("张三11",new Employee("张三11",32,1000.12, Employee.Status.BUSY));

        mapList.add(map1);
        mapList.add(map2);
        mapList.add(map3);
        mapList.add(map4);


    }

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

    @Test
    public void testMapList(){
        //根据姓名和年龄分组，然后求薪水和
        Collection<List<Map<String, Object>>> values = mapList.stream().collect(Collectors.groupingBy(e -> e.get("name") + "_" + e.get("age"))).values();
        List<Map<String, Object>> collect = values.stream()
                .map(mapList1 -> {
                    Map<String, Object> sampleData = mapList1.get(0);
                    sampleData.put("salaryCount", mapList1.stream().map(s -> new BigDecimal(s.get("salary").toString())).reduce(BigDecimal.ZERO, BigDecimal::add));
                    return sampleData;
                }).collect(Collectors.toList());
        System.out.println(collect);
        List<Map<String, Object>> collect1 =  mapList.stream().collect(Collectors.groupingBy(e -> e.get("name") + "_" + e.get("age"))).values().stream()
                .map(list -> {
                    Map<String, Object> sampleData = list.get(0);
                   // mapList1.stream().filter(m->m.get("age"))
                    sampleData.put("salaryNum", list.stream().map(m->m.get("salary")).distinct().count());
                    //sampleData.put("ageNum", list.stream().map(m->m.get("age")).distinct().count());
                    return sampleData;
                }).collect(Collectors.toList());
        System.out.println(collect1);
    }
    @Test
    public void testMapList2(){
        Map<String, Map<String, Object>> collect = mapList.stream().collect(Collectors.toMap(m -> (m.get("name") + "_" + m.get("age")), map1 -> map1, (key1, key2) -> key1));
        System.out.println(collect);
        List<Map<String, Object>> list2 = mapList.stream().collect(
                Collectors.collectingAndThen(Collectors.toCollection(() ->new TreeSet<>(Comparator.comparing(m->m.get("name").toString()))),ArrayList::new));
        System.out.println(list2);

    }
    @Test
    public void testMain(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long time =1624416750000L;
        Date date = new Date(time);
        String format = dateFormat.format(date);
        System.out.println(format);
        long l = 57600000L;
        long l2=3600000L;
        System.out.println(l / l2);


    }

    public static void main(String[] args) {
        try(Connection connection=getConnection();
            Statement statement = connection.createStatement();
            Statement st = Optional.ofNullable(connection).map(conn->createState(conn)).orElseThrow(()->new Exception("connection为空！"));
            ResultSet resultSet=st.executeQuery("select * from t")){

            System.out.println("........");

        }catch (Exception e){

        }
    }

    private static Connection getConnection() {
        return null;
    }

    private static Statement createState(Connection connection){
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

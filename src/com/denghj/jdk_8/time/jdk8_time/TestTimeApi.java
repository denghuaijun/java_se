package com.denghj.jdk_8.time.jdk8_time;

import org.junit.Test;

import java.time.*;

/**
 * LocalDate、LocalTime、LocalDateTime类的实例是不可变的对象，分别表示使用ISO-8601日历系统的日期、时间及日期和时间
 */
public class TestTimeApi {
    //LocalDate、LocalTime、LocalDateTime
    @Test
    public void test1(){
        //获取当前系统时间
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);//2021-01-05T18:35:05.834

        LocalDateTime of = LocalDateTime.of(2020, 1, 5, 18, 12, 13);
        System.out.println(of);//2020-01-05T18:12:13

        //对于年、月、日、时、分、秒的操作
        //加1年
        LocalDateTime localDateTime1 = localDateTime.plusYears(1);
        System.out.println(localDateTime1);
        //减2月
        System.out.println(localDateTime.minusDays(2));
        //获取年月日
        System.out.println(localDateTime.getDayOfYear());//其它以此类推
    }
    //2、Instant :时间戳 （以Unix 元年：1970年1月1日00：00：00 到某个时间的毫秒值）
    @Test
    public  void test2(){
        Instant instant = Instant.now();
        System.out.println(instant);//2021-01-05T10:43:33.811Z 默认获取UTC（世界协调时间） 时区（与本地时间差8h）
        //获取毫秒值
        System.out.println(instant.toEpochMilli());//1609843527872
        Instant instant1 = Instant.ofEpochSecond(60);//1970-01-01T00:16:40Z,相交于unix元年做了一下加60s运算
        System.out.println(instant1);
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));//带偏移量的运算
        System.out.println(offsetDateTime);//2021-01-05T18:43:33.811+08:00
    }
    //3、Duration :计算俩个时间戳的间隔

    @Test
    public void test3() throws InterruptedException {
        System.out.println("----------------------------时间间隔1-----------------");
        Instant instant1 = Instant.now();
        Thread.sleep(1000);
        Instant instant2=Instant.now();
        //获取秒、纳秒都使用get,时间间隔
        System.out.println(Duration.between(instant1,instant2).toMillis()+"毫秒");
        System.out.println("----------------------------时间间隔2-----------------");
        LocalTime localTime1=LocalTime.now();
        Thread.sleep(2000);
        LocalTime localTime2=LocalTime.now();
        System.out.println(Duration.between(localTime1,localTime2).toMillis());
        System.out.println("----------------------------日期间隔-----------------");
        //Period :计算俩个日期之间的间隔 获取对应的  年、月、日
        LocalDate localDateTime1=LocalDate.of(2015,2,3);
        LocalDate localDateTime2=LocalDate.now();
        System.out.println(Period.between(localDateTime1,localDateTime2).getYears());
    }

}

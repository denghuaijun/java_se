package com.denghj.jdk_8.time.时间格式化与时区处理;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

/**
 * 时间格式化与，时区处理API
 */
public class TestDateFomaterAndZone {

    //使用ISO国际化标准格式
    public  static final DateTimeFormatter ddd = DateTimeFormatter.ISO_DATE_TIME;
    //自定义的格式化格式
    public static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Test
    public void  test(){
        System.out.println("--------------将日期转化为字符串----------------");
        LocalDateTime l = LocalDateTime.now();
        String format = l.format(ddd);
        System.out.println(format);//2021-01-06T10:51:36.53 没啥区别
        String format1 = l.format(dtf);  //====》与下面的等价
        String format2 = dtf.format(l);
        System.out.println(format2);//2021-01-06 10:52:20
        System.out.println("-----------将字符传解析为日期-------------------");
        LocalDateTime ldt = LocalDateTime.now();
        LocalDateTime parse = ldt.parse(format2, dtf);//2021-01-06T10:57:28
        System.out.println(parse);

    }
    //操作时区 ZoneDate ZoneTime ZoneDateTime
    @Test
    public void testZone(){
        //获取所有时区
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        //availableZoneIds.forEach(System.out::println);
        //指定时区构建时间
        LocalDateTime now = LocalDateTime.now(ZoneId.of("America/Atikokan"));
        //System.out.println(now);//2021-01-05T22:01:32.372
        //时区转换
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("America/Atikokan"));
        System.out.println("zonedDateTime:"+zonedDateTime);//zonedDateTime:2021-01-06T11:03:25.056-05:00[America/Atikokan]
    }
}

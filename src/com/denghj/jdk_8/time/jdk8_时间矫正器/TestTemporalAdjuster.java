package com.denghj.jdk_8.time.jdk8_时间矫正器;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * TemporalAdjuster :时间矫正器,有时我们可能需要获取例如：将日期调整到“下个周日”等操作。
 *TemporalAdjusters: 时间矫正器实现
 */
public class TestTemporalAdjuster {


    @Test
    public void testTemp(){
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        LocalDateTime localDateTime1 = localDateTime.withDayOfYear(36);
        //System.out.println(localDateTime1);
        //使用时间矫正器来获取下一个周日

        LocalDateTime with = localDateTime.with(TemporalAdjusters.dayOfWeekInMonth(1,DayOfWeek.MONDAY));
        System.out.println(with);
        //由于TemporalAdjuster 是一个函数式function接口，故可以自定义矫正器,获取下一个工作日
        LocalDateTime localDateTime22 = localDateTime.with((temporal) -> {
            LocalDateTime localDateTime3 = (LocalDateTime) temporal;
            System.out.println("localDateTime3="+localDateTime3);
            DayOfWeek dayOfWeek = localDateTime3.getDayOfWeek();
            System.out.println("dayOfWeek="+dayOfWeek);
            if(dayOfWeek.equals(DayOfWeek.FRIDAY)){
                return localDateTime3.plusDays(3);
            }else if (dayOfWeek.equals(DayOfWeek.SATURDAY)){
                return localDateTime3.plusDays(2);
            }else {
                return localDateTime3.plusDays(1);
            }
        });
        System.out.println("localDateTime22==:"+localDateTime22);

    }
}

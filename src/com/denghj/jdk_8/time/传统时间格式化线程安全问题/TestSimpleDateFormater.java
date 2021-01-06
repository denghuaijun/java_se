package com.denghj.jdk_8.time.传统时间格式化线程安全问题;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * test1：验证使用传统的SimpleDateFormat进行时间格式转换出现线程安全问题
 * test2：使用传统的ThreadLocal的方式解决线程安全问题
 * test3：使用jdk8新的时间格式转化类进行解决
 */
public class TestSimpleDateFormater {
    @Test
    public void test1(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr="2020-01-05 18:00:00";
        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<Date>> futureList = new ArrayList<>();
        for (int i=0;i<10;i++){
            Future<Date> submit = executorService.submit(() -> sdf.parse(dateStr));
            futureList.add(submit);
        }
        futureList.forEach(dateFuture -> {
            try {
                System.out.println(dateFuture.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();
    }
    /*
    Sun Jan 05 18:00:00 CST 2020
     */
    @Test
    public void test2(){
        String dateStr="2020-01-05 18:00:00";
        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<Date>> futureList = new ArrayList<>();
        for (int i=0;i<10;i++){
            Future<Date> submit = executorService.submit(() -> ThreadLocalDateFormat.convert(dateStr));
            futureList.add(submit);
        }
        futureList.forEach(dateFuture -> {
            try {
                System.out.println(dateFuture.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();

    }
    /*
    2020-01-05T18:00  LocalDateTime
    2020-01-05 LocalDate
     */
    @Test
    public void test3(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String dateStr="2020-01-05 18:00:00";
        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<LocalDate>> futureList = new ArrayList<>();
        for (int i=0;i<10;i++){
            Future<LocalDate> submit = executorService.submit(() -> LocalDate.parse(dateStr,dateTimeFormatter));
            futureList.add(submit);
        }
        futureList.forEach(dateFuture -> {
            try {
                System.out.println(dateFuture.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();

    }
}

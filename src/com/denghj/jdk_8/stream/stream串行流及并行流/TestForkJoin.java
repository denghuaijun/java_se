package com.denghj.jdk_8.stream.stream串行流及并行流;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

/**
 * 测试fork/join 进行拆分汇总测试类
 */
public class TestForkJoin {

    /**
     * 测试fork Join
     */
    @Test
    public void testForkJoin(){
        Instant instant = Instant.now();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinCal(0,100000000L);
        Long invoke = pool.invoke(task);
        System.out.println("sum=:"+invoke);
        Instant end = Instant.now();
        System.out.println("forkJoin耗时："+Duration.between(instant,end).toMillis());//1200毫秒
    }

    /**
     * 测试for循环
     */
    @Test
    public void testForEach(){
        Instant instant = Instant.now();
        long sum = 0;
        for (long i=0;i<=100000000;i++){
            sum +=i;
        }
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println("forEach耗时："+Duration.between(instant,end).toMillis());//81毫秒
    }
    /**
     * 测试java8并行流
     */
    @Test
    public void testStream(){
        Instant instant = Instant.now();
        LongStream.rangeClosed(0,100000000000L)
                .parallel()
                .reduce(0,Long::sum);
        Instant end = Instant.now();
        System.out.println("java8并行流耗时："+Duration.between(instant,end).toMillis());//1062毫秒
    }
}

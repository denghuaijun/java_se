package com.denghj.注解and反射.reflect;

import java.lang.reflect.Method;

/**
 * 对象性能测试
 */
public class FunctionTest {

    /**
     * 测试普通方式执行代码块
     */
    public static void test01(){
        User user = new User();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            user.getUserName();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("使用普通方式执行10亿次耗时："+(endTime-startTime));

    }
    /**
     * 测试反射打开检测方式执行代码块
     */
    public static void test02()throws Exception{
        User user = new User();
        Class userClass = user.getClass();
        Method getUserName = userClass.getMethod("getUserName", null);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            getUserName.invoke(user,null);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("反射打开检测方式执行10亿次耗时："+(endTime-startTime));

    }
    /**
     * 测试反射关闭检测方式执行代码块
     */
    public static void test03()throws Exception{
        User user = new User();
        Class userClass = user.getClass();
        Method getUserName = userClass.getMethod("getUserName", null);
        getUserName.setAccessible(true);//关闭监测
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            getUserName.invoke(user,null);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("反射关闭检测方式执行10亿次耗时："+(endTime-startTime));

    }

    public static void main(String[] args)throws Exception {
        test01();
        test02();
        test03();
        //使用普通方式执行10亿次耗时：8
        //反射打开检测方式执行10亿次耗时：6133
        //反射关闭检测方式执行10亿次耗时：2771
    }
}

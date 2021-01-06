package com.denghj.shejimoshi.staticproxy;

/**
 * @author dhj
 * @Description 代理设计模式：使用代理类面向对象，使用代理对象控制实体对象
 * ，并可以访问其具体方法，同时可以在调用这个方法前和后进行处理逻辑如校验，日志跟踪，AOP
 * 面向切面编程就是使用的代理设计模式----》代理模式分为动态代理、静态代理（jdk动态代理、Cglib代理、javaassit代理）
 *静态代理需要自己写代理类，动态代理不需要
 * @Date Create in 16:20 2019/8/21
 */
public interface Hose {

    public void mai();
}

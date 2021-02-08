package com.denghj.注解and反射.reflect;

/**
 * 分析类加载机制
 */
public class Test03 {
    static {
        System.out.println("这是Test03静态代码快。。。");
    }

    public static void main(String[] args) {
        A a = new A();
        System.out.println(A.m);
        /*
        输出结果：
        这是Test03静态代码快。。。
        这是类A的静态代码块。。。
        这是类A的无参构造。。。
        100
         */
        /*
          由结果可知：
          1、Test03、A类在加载的时候将类的静态变量，静态代码块，常量池，代码块都存放在方法区，通时构成各自对应的类对象
          2、然后进行对静态变量进行初始化默认值m=0，进行链接
          3、初始化在栈中进行赋值合并
          调用clinit(){
            这是Test03静态代码快。。。
            这是类A的静态代码块。。。
            这是类A的无参构造。。。
            m=300
            m=100
          }

         */
    }
}

class A{
    static {
        System.out.println("这是类A的静态代码块。。。");
        m=300;
    }
    static int m =100;
    public A(){
        System.out.println("这是类A的无参构造。。。");
    }
}

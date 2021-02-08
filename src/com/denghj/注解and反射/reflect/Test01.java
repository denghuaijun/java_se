package com.denghj.注解and反射.reflect;

/**
 * 反射初始
 * 一个类在内存中只有一个class对象
 * 一个类被加载后，类的整个结构信息都在封装在class对象中
 */
public class Test01 {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> c1 = Class.forName("com.denghj.注解and反射.reflect.User");
        System.out.println("c1==:"+c1);
        User user = new User();
        Class<? extends User> aClass = user.getClass();
        System.out.println(aClass);
        Class<?> c2 = Class.forName("com.denghj.注解and反射.reflect.User");
        System.out.println(c1==c2);//如果为true代表，一个类在内存中只有一个class对象
    }
}


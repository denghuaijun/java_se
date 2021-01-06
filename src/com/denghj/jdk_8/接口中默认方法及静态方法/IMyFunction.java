package com.denghj.jdk_8.接口中默认方法及静态方法;

/**
 * jdk8中针对于接口可以增加使用default修饰的具体方法及静态方法
 */
public interface IMyFunction {
    default String getString(){
        return "啊啊啊";
    }
    public static void test(){
        System.out.println("接口中的静态方法");
    }
}

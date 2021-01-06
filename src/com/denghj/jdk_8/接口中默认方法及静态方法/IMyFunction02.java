package com.denghj.jdk_8.接口中默认方法及静态方法;

/**
 * jdk8中针对于接口可以增加使用default修饰的具体方法及静态方法
 */
public interface IMyFunction02 {
    default String getString(){
        return "哈哈哈";
    }
}

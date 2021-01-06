package com.denghj.jdk_8.lambda.基本语法;

/**
 * 策略模式抽离接口
 */
public interface IEmpStrategy<T> {
    public boolean compare(T t);
}

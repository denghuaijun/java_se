package com.denghj.shejimoshi.factory.factorymethod;

import com.denghj.shejimoshi.factory.Animal;

/**
 * 使用工厂方法获取animal对象,这样在有新的动物就不会有修改原来的类，只需要新增即可，这样代码冗余是增加了
 */
public interface FactoryMethod {
    Animal getAnimal();
}

package com.denghj.shejimoshi.factory.abstractFactory;

/**
 * 抽象工厂类，作为产品的抽象，用来生产产品
 */
public interface IProductFactory {
    IPhoneProduct getPhoneProduct();
    IRouterProduct getRouterProduct();
}

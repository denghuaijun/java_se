package com.denghj.shejimoshi.factory.abstractFactory;

public class Main {
    public static void main(String[] args) {
        IProductFactory factory = new XiaoMiFactory();
        IPhoneProduct phoneProduct = factory.getPhoneProduct();
        phoneProduct.name();
        IRouterProduct routerProduct = factory.getRouterProduct();
        routerProduct.name();
    }
}

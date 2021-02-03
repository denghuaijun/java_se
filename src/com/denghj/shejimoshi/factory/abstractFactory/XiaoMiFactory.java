package com.denghj.shejimoshi.factory.abstractFactory;

public class XiaoMiFactory implements IProductFactory {
    @Override
    public IPhoneProduct getPhoneProduct() {
        return new XiaoMiPhone();
    }

    @Override
    public IRouterProduct getRouterProduct() {
        return new XiaoMiRouter();
    }
}

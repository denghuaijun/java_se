package com.denghj.shejimoshi.factory.abstractFactory;

public class HuaWeiFactory implements IProductFactory {
    @Override
    public IPhoneProduct getPhoneProduct() {
        return new HuaWeiPhone();
    }

    @Override
    public IRouterProduct getRouterProduct() {
        return new HuaWeiRouter();
    }
}

package com.denghj.shejimoshi.factory.abstractFactory;

public class XiaoMiRouter implements IRouterProduct {
    @Override
    public void start() {
        System.out.println("小米路由器。。开启");
    }

    @Override
    public void name() {
        System.out.println("小米路由器。。");
    }

    @Override
    public void setting() {
        System.out.println("小米路由器。。设置");

    }
}

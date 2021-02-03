package com.denghj.shejimoshi.factory.abstractFactory;

public class HuaWeiRouter implements IRouterProduct {
    @Override
    public void start() {
        System.out.println("华为路由器开机。。。");
    }

    @Override
    public void name() {
        System.out.println("这是华为路由器");
    }

    @Override
    public void setting() {
        System.out.println("华为路由器设置");
    }
}

package com.denghj.shejimoshi.factory.abstractFactory;

public class XiaoMiPhone implements IPhoneProduct {
    @Override
    public void start() {
        System.out.println("小米开机。。。");
    }

    @Override
    public void name() {
        System.out.println("this is xiao mi...");
    }

    @Override
    public void sendMsg() {
        System.out.println("小米手机发送消息。。。");
    }
}

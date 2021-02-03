package com.denghj.shejimoshi.factory.abstractFactory;

public class HuaWeiPhone implements IPhoneProduct {
    @Override
    public void start() {
        System.out.println("华为手机开机。。。");
    }

    @Override
    public void name() {
        System.out.println("这是华为手机。。。");
    }

    @Override
    public void sendMsg() {
        System.out.println("华为手机发送信息。。。");
    }
}

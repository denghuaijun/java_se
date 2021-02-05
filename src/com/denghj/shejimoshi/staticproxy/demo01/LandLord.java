package com.denghj.shejimoshi.staticproxy.demo01;

/**
 * 房东
 */
public class LandLord implements Hose{
    @Override
    public void rent() {
        System.out.println("我是房东我要租房。。。");
    }
}

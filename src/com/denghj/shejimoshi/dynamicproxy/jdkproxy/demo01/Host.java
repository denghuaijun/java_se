package com.denghj.shejimoshi.dynamicproxy.jdkproxy.demo01;

/**
 * 真实角色
 */
public class Host implements  Hose {
    @Override
    public void rent() {
        System.out.println("我是房东，我要租房。。。");
    }
}

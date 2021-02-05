package com.denghj.shejimoshi.dynamicproxy.jdkproxy.demo01;

public class Test {
    public static void main(String[] args) {
        //真实角色
        Host host = new Host();
        //使用动态代理生成代理类
        ProxyInvocationHandler pih = new ProxyInvocationHandler();
        pih.setHose(host);
        Hose proxyInstance = (Hose) pih.getProxyInstance();
        proxyInstance.rent();
    }
}

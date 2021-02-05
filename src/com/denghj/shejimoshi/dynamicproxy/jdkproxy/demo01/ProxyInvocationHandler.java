package com.denghj.shejimoshi.dynamicproxy.jdkproxy.demo01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 使用一个与代理关联类相关的类产生：
 * 1、代理类实例
 * 2、处理代理方法并进行返回结果，使用反射
 */
public class ProxyInvocationHandler implements InvocationHandler {

    private Hose hose;

    public void setHose(Hose hose) {
        this.hose = hose;
    }

    /**
     *
     * @param proxy 被代理的对象，我们这里代理的是个接口，代码租房的这一类业务
     * @param method 要执行的代理对象的方法
     * @param args 代理的参数
     * @return 返回执行结果
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("你是我的小呀小苹果。。。");
        Object invoke = method.invoke(hose, args);
        return invoke;
    }

    /**
     * 通过PRoxy类的静态实例方法产生一个代理对象
     * @return
     */
    public Object getProxyInstance(){
        Object instance = Proxy.newProxyInstance(this.getClass().getClassLoader(), hose.getClass().getInterfaces(), this);
        return  instance;
    }
}

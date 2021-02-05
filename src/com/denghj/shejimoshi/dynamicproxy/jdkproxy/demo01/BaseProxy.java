package com.denghj.shejimoshi.dynamicproxy.jdkproxy.demo01;

import com.denghj.shejimoshi.staticproxy.demo02.UserService;
import com.denghj.shejimoshi.staticproxy.demo02.UserServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 通用的jdk动态代理处理类
 */
public class BaseProxy implements InvocationHandler {
    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String name = method.getName();
        System.out.println("正在执行的方法名为：---"+name);
        Object invoke = method.invoke(target, args);
        return invoke;
    }

    public Object getProxyInstance(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }

    public static void main(String[] args) {
        UserServiceImpl userServiceImp = new UserServiceImpl();
        BaseProxy proxy = new BaseProxy();
        proxy.setTarget(userServiceImp);
        UserService userService = (UserService) proxy.getProxyInstance();
        userService.add();
    }
}

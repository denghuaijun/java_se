package com.denghj.shejimoshi.jdkproxy;

import com.denghj.shejimoshi.staticproxy.Hose;
import com.denghj.shejimoshi.staticproxy.XiaoMing;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author dhj
 * @Description jdk动态代理主要使用反射技术，生成代理对象很快但是执行过程效率不高
 * @Date Create in 16:32 2019/8/21
 */
public class JdkProxy implements InvocationHandler {

    private Object target;

    public JdkProxy(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("使用jdk动态代理开始监听---买房");
        Object invoke = method.invoke(target, args);
        System.out.println("使用jdk动态代理结束监听---买房");
        return invoke;
    }

    public static void main(String[] args) {
        JdkProxy jdkProxy = new JdkProxy(new XiaoMing());
        Hose hose = (Hose) Proxy.newProxyInstance(XiaoMing.class.getClassLoader(),XiaoMing.class.getInterfaces(),jdkProxy);
        hose.mai();
    }
}

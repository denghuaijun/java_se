package com.denghj.shejimoshi.staticproxy.demo01;

public class Test {
    public static void main(String[] args) {
        Proxy proxy = new Proxy(new LandLord());
        proxy.rent();
    }
}

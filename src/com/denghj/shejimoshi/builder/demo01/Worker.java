package com.denghj.shejimoshi.builder.demo01;

/**
 * 具体的工人
 */
public class Worker extends Builder {
    private Home home;

    public Worker() {
        home = new Home();
    }

    @Override
    void pay() {
        home.setPay("花费100");
    }

    @Override
    void address() {
        home.setAddress("北京市");
    }

    @Override
    void setType() {
        home.setHomeType("平房");
    }

    @Override
    Home build() {
        return home;
    }
}

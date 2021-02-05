package com.denghj.shejimoshi.staticproxy.demo02;

/**
 * 真实对象
 */
public class UserServiceImpl implements UserService
{
    @Override
    public void add() {
        System.out.println("这是add");
    }

    @Override
    public void delete() {
        System.out.println("这是delete");

    }

    @Override
    public void update() {
        System.out.println("这是update");

    }

    @Override
    public void select() {
        System.out.println("这是select");

    }
}

package com.denghj.jdk_8.接口中默认方法及静态方法;

import org.junit.Test;

public class TestDefaultInterface {

    @Test
    public void test(){
        SubClass subClass = new SubClass();
        String string = subClass.getString();
        System.out.println(string);
    }
}

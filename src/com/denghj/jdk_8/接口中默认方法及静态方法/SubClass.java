package com.denghj.jdk_8.接口中默认方法及静态方法;

public class SubClass /*extends MyClass*/ implements IMyFunction,IMyFunction02 {
    @Override
    public String getString() {
        return IMyFunction.super.getString();
    }
}

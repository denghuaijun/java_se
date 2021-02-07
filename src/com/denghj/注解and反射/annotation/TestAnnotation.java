package com.denghj.注解and反射.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

public class TestAnnotation {

    @MyAnnotation(schools = {"上海交大"})
    public  void test(){
        System.out.println("测试注解。。。。");
    }
}

@Documented
@Target(value = {ElementType.TYPE,ElementType.METHOD})
@interface MyAnnotation{
    int age() default 0;
    String name() default "";
    String[] schools();

}

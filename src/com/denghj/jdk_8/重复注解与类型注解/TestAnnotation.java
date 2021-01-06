package com.denghj.jdk_8.重复注解与类型注解;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.stream.Stream;

/**
 * jdk8新特性，新增重复注解与类型注解(TYPE_PARAMETER)
 */
public class TestAnnotation {



    @MyAnnotation("hello")
    @MyAnnotation("world")
    public  void testAnnotation(@MyAnnotation("abc") String string){

    }

    @Test
    public void test() throws NoSuchMethodException {
        Class<TestAnnotation> aClass = TestAnnotation.class;
        Method method = aClass.getMethod("testAnnotation", String.class);
        MyAnnotation[] myAnnotations = method.getAnnotationsByType(MyAnnotation.class);
        Stream.of(myAnnotations).forEach(myAnnotation -> System.out.println(myAnnotation.value()));
    }
}

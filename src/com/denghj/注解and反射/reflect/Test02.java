package com.denghj.注解and反射.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * 通过反射获取类，方法参数，返回值的泛型信息
 */
public class Test02 {
    public  void test01(Map<String,User> map, List<User> userList){
        System.out.println("测试入参的的泛型信息");
    }
    public Map<String,List<User>> test02(){
        System.out.println("测试方法返回值的泛型信息");
        return null;
    }

    public static void main(String[] args) throws Exception{
        Class<Test02> test02Class = Test02.class;

        Method test01Method = test02Class.getDeclaredMethod("test01", Map.class, List.class);
        Type[] genericParameterTypes = test01Method.getGenericParameterTypes();
        for (Type genericParameterType : genericParameterTypes) {
            System.out.println(genericParameterType);
            if (genericParameterType instanceof ParameterizedType){
                Type[] actualTypeArguments = ((ParameterizedType) genericParameterType).getActualTypeArguments();
                for (Type actualTypeArgument : actualTypeArguments) {
                    System.out.println(actualTypeArgument);
                    Class<String> stringClass = (Class<String>) actualTypeArgument;
                }
            }
        }
        System.out.println("-========================================返回值注解信息==============");

        Method test02Method = test02Class.getDeclaredMethod("test02",null);
        Type genericReturnType = test02Method.getGenericReturnType();
        Class<? extends Type> aClass = genericReturnType.getClass();
        System.out.println(aClass);
        Type[] actualTypeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();
        for (Type actualTypeArgument : actualTypeArguments) {
            Class<? extends Type> aClass1 = actualTypeArgument.getClass();
            System.out.println(aClass1);
            if (actualTypeArgument instanceof ParameterizedType){
                Type[] actualTypeArguments1 = ((ParameterizedType) actualTypeArgument).getActualTypeArguments();
                System.out.println(actualTypeArguments1[0]);
            }
        }

    }
}

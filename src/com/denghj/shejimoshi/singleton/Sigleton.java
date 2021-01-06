package com.denghj.shejimoshi.singleton;

/**
 * @author dhj
 * @Description 设计模式，单例模式
 * @Date Create in 9:47 2019/8/21
 */
public class Sigleton {

    /**
    *@author dhj
    *@Description 单例模式  懒汉式
    *@Date : 9:51 2019/8/21
    */
    static class SigletonLh{

         private static SigletonLh sigletonLh;//私有的成员变量
         private SigletonLh(){};//私有的构造函数
         public static SigletonLh getSigletonLh(){//公有静态的方法
             if (sigletonLh == null){
                 synchronized (SigletonLh.class){
                     sigletonLh =  new SigletonLh();
                 }
             }
             return sigletonLh;
         }

    }

    /**
    *@author dhj
    *@Description  单例模式  饿汉式（默认是线程安全的）
    *@Date : 13:52 2019/8/21
    */

    static class SingletonEh{

        private static final SingletonEh singletonEh = new SingletonEh();

        public static SingletonEh getSingletonEh(){
            return singletonEh;
        }

    }



    public static void main(String[] args) {
        SigletonLh s1 = SigletonLh.getSigletonLh();
        SigletonLh s2 = SigletonLh.getSigletonLh();
        System.out.println("Sigleton.main====>"+(s1==s2));
        SingletonEh s3 = SingletonEh.getSingletonEh();
        SingletonEh s4 = SingletonEh.getSingletonEh();
        System.out.println("Sigleton.main====>"+(s3==s4));

    }
}

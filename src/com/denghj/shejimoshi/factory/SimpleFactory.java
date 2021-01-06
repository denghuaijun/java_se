package com.denghj.shejimoshi.factory;

/**
 * @author dhj
 * @Description 简单工厂方法
 * @Date Create in 15:53 2019/8/21
 */
public class SimpleFactory {

    public static Animal cerateAnimalObj(String name){
        Animal animal=null;
        if (name.equals("cat")){
            animal = new cat();
        }else if (name.equals("dog")){
            animal = new dog();
        }
        return animal;
    }

    public static void main(String[] args) {
        Animal animal = SimpleFactory.cerateAnimalObj("cat");
        Animal animal2 = SimpleFactory.cerateAnimalObj("dog");
        animal.run();
        animal2.run();
    }

}

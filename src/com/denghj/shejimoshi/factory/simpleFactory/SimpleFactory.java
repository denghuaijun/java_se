package com.denghj.shejimoshi.factory.simpleFactory;

import com.denghj.shejimoshi.factory.Animal;
import com.denghj.shejimoshi.factory.cat;
import com.denghj.shejimoshi.factory.dog;

/**
 * @author dhj
 * @Description 简单工厂方法，违反了开闭原则（对扩展开放，对修改关闭），一旦增加新的动物了就会出现修改代码的情况
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

    //也可以换成这样。。。。。。。。。。。
    /*public  static Animal createCat(){
        return new cat();
    }*/

    public static void main(String[] args) {
        Animal animal = SimpleFactory.cerateAnimalObj("cat");
        Animal animal2 = SimpleFactory.cerateAnimalObj("dog");
        animal.run();
        animal2.run();
    }

}

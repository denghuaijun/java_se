package com.denghj.shejimoshi.factory;

/**
 * @author dhj
 * @Description 工厂类
 * @Date Create in 15:41 2019/8/21
 */
public class Factory implements Animal  {
    private Animal animal;
    public Factory(Animal animal){
        this.animal = animal;
    }

    @Override
    public void run() {
       animal.run();
    }

    public static void main(String[] args) {
        new Factory(new dog()).run();
        new Factory(new cat()).run();
    }
}

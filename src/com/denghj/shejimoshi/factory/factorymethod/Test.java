package com.denghj.shejimoshi.factory.factorymethod;

import com.denghj.shejimoshi.factory.Animal;

public class Test {
    public static void main(String[] args) {
        Animal animal= new DogFactory().getAnimal();
        Animal animal2= new CatFactory().getAnimal();

        animal.run();
        animal2.run();

    }
}

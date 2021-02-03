package com.denghj.shejimoshi.factory.factorymethod;

import com.denghj.shejimoshi.factory.Animal;
import com.denghj.shejimoshi.factory.dog;

public class DogFactory implements FactoryMethod {
    @Override
    public Animal getAnimal() {
        return new dog();
    }
}

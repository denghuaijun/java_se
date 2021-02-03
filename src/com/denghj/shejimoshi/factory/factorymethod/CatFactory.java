package com.denghj.shejimoshi.factory.factorymethod;

import com.denghj.shejimoshi.factory.Animal;
import com.denghj.shejimoshi.factory.cat;

public class CatFactory implements FactoryMethod {
    @Override
    public Animal getAnimal() {
        return new cat();
    }
}

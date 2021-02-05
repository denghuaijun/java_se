package com.denghj.shejimoshi.bridge;

public class Test
{
    public static void main(String[] args) {
        //想要个苹果笔记本
        IBrand appBrand = new Apple();
        Computer laptop = new Laptop(appBrand);
        laptop.getComputerName();
        //联想台式机
        IBrand lenovo = new Lenovo();
        Computer desktop = new Desktop(lenovo);
        desktop.getComputerName();
    }
}

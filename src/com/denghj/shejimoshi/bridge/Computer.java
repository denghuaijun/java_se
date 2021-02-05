package com.denghj.shejimoshi.bridge;

/**
 * computer类就相当于桥梁，将实际的类型和品牌分离开
 */
public abstract class Computer {
    //组合品牌
    protected IBrand brand;

    public Computer(IBrand brand) {
        this.brand = brand;
    }

    public void getComputerName() {
        brand.getName();
    }
}

    /**
     * 子类通过父类连接到了品牌类
     */
     class Desktop extends Computer{

        public Desktop(IBrand brand) {
            super(brand);
        }

        @Override
        public void getComputerName() {

            super.getComputerName();
            System.out.println("台式机");

        }
    }
    /**
     * 笔记本
     */
     class Laptop extends Computer{

        public Laptop(IBrand brand) {
            super(brand);
        }
        @Override
        public void getComputerName() {

            super.getComputerName();
            System.out.println("笔记本");

        }

    }



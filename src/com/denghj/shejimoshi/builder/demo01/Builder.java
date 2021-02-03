package com.denghj.shejimoshi.builder.demo01;

/**
 * 作为构造器，用来建房子,建造者抽象类，不同的具体工人能够建造不同的房子
 */
public abstract class Builder {

    //建造的过程
    abstract void pay();//花费多少钱
    abstract void address();//房子的地址
    abstract void setType();//房子的户型

    //用来建造房子
    abstract Home build();



}

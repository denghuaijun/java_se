package com.denghj.shejimoshi.builder.demo01;

/**
 * 指挥核心，负责指挥构建一个工程，工程如何构建，由她决定
 */
public class Director {

    public Home buildHome(Builder builder){
        builder.address();
        builder.pay();
        builder.setType();
        return builder.build();
    }
}

package com.denghj.shejimoshi.builder.demo03;

/**
 * 抽象的构造器
 */
public abstract class Builder {
     abstract Builder setUserName(String name);

    abstract UserEntity build();
}

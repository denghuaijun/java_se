package com.denghj.shejimoshi.builder.demo02;

/**
 * 抽象构建者
 */
public abstract class Builder {

    abstract Builder buildA(String a);
    abstract Builder buildB(String b);
    abstract Builder buildC(String c);

    public abstract Product build();


}

package com.denghj.shejimoshi.builder.demo02;

public class Worker extends Builder {
    private Product product;
    Worker(){
        product = new Product();
    }
    @Override
    Builder buildA(String a) {
        product.setA(a);
        return this;
    }

    @Override
    Builder buildB(String b) {
        product.setB(b);
        return this;
    }

    @Override
    Builder buildC(String c) {
        product.setC(c);
        return this;
    }

    @Override
    public Product build() {
        return product;
    }

}

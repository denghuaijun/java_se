package com.denghj.shejimoshi.builder.demo02;

public class Test {
    public static void main(String[] args) {
        //直接在用户中进行消费充当director
        Product product = new Worker().buildA("大谎报").build();
        System.out.println(product.toString());
        Product build = new Worker().build();
        System.out.println(build.toString());
    }
}

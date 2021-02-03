package com.denghj.shejimoshi.builder.demo01;


public class Test {
    public static void main(String[] args) {
        Director director = new Director();
        Home home = director.buildHome(new Worker());
        System.out.println(home.toString());
    }
}

package com.denghj.thread;

import java.util.Random;

public class shop {

     private String name;
     private Random random = new Random();

     //模拟延迟
    public  void delay(){

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //模拟获取价格服务
    private double calulatePrice(String product){
            delay();

            return  random.nextDouble()*product.charAt(0) + product.charAt(1);

    }


}

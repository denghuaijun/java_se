package com.denghj.shejimoshi.prototype.demo02;

import java.util.Date;

public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        //测试浅克隆
        Date date = new Date();
        Video video = new Video("金瓶梅",date);
        //原始的方式是使用new一个新的对象，现在我们使用克隆一个对象
        Video video1 = (Video) video.clone();
        System.out.println(video.toString());
        System.out.println(video1.toString());
        //我们可以自定义我们克隆后的对象属性
        //video1.setName("java编程思想");
        //System.out.println(video1.toString());
        //假如我们修改date对象的值我们看下效果，预期：只是原型对象修改，而克隆的对象还是以前克隆的才对
        date.setTime(21321321);
        System.out.println("=====================");
        System.out.println(video.toString());
        System.out.println(video1.toString());
        //但是结果发现都变了，其实我们只是改变了原型的引用，但是复制的对象也进行了引用，这样是有问题的，所以称为线克隆
    }
}

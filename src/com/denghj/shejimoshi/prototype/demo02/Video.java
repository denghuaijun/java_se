package com.denghj.shejimoshi.prototype.demo02;

import java.util.Date;

/**
 * 原型类
 */
public class Video implements Cloneable{
    private String name;//电影的名称
    private Date createTime;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object clone = super.clone();
        Video video = (Video) clone;
        //深克隆的方式是序列化或者反序列化及使用属性进行克隆
        video.createTime = (Date) createTime.clone();
        return clone;
    }

    public Video(String name, Date createTime) {
        this.name = name;
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Video{" +
                "name='" + name + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}

package com.denghj.shejimoshi.builder.demo03;

public class BulidWrap extends Builder {
    private UserEntity userEntity;

    public BulidWrap() {
        userEntity = new UserEntity();
    }

    @Override
    Builder setUserName(String name) {
        userEntity.setName(name);
        return this;
    }

    @Override
    UserEntity build() {
        return userEntity;
    }

    public static void main(String[] args) {
        //UserEntity build = new BulidWrap().build();
        UserEntity build = new BulidWrap().setUserName("等画家").build();
        System.out.println(build.toString());
    }
}

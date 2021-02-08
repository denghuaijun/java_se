package com.denghj.注解and反射.reflect;

public class User {

    private int id;
    private int age;
    private String userName;

    public User() {
    }

    public User(int id, int age, String userName) {
        this.id = id;
        this.age = age;
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", userName='" + userName + '\'' +
                '}';
    }
}

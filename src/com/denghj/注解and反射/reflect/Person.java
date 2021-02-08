package com.denghj.注解and反射.reflect;

public class Person {
    public  String name;

    Person(){

    }
    public Person(String name){
        this.name=name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
 class Student extends Person{
    public Student(){
        this.name="学生";
    }
}
class Teacher extends Person{
    Teacher(){
        this.name="老师";
    }
}

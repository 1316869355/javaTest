package com.up72.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/2.
 */
public class Person implements Serializable{
    private Long id;
    private String name;
    private int age; //默认 1岁
    private boolean sex; // true 男，false 女

    public Person (String name){
        this(name, 1, false);
    }
    public Person(String name, int age, boolean sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }
}

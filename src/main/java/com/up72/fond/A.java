package com.up72.fond;

/**
 * Created by Administrator on 2016/7/19.
 */
public class A {
    private String name;
    private int age;
    private boolean sex;

    public A() {
        System.out.println("A default construct ==== 1");
    }

    public A(String name, int age, boolean sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        System.out.println("A construct with variables ==== 1");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof A)) return false;

        A a = (A) o;

        if (getAge() != a.getAge()) return false;
        if (isSex() != a.isSex()) return false;
        return getName().equals(a.getName());

    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getAge();
        result = 31 * result + (isSex() ? 1 : 0);
        return result;
    }
}

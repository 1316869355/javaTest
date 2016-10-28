package com.up72.fond.reflex;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/29.
 */
public class NewAObj {
    public static void main(String[] args) {
        List<Employee> empList = new ArrayList<Employee>();
        for (int i = 0; i < 10; i++) {
            empList.add(recuitAEmployee());
        }
        System.out.println(empList.size());

        for (Employee e : empList) {
            System.out.println(e.toString());
        }
    }

    public static Employee recuitAEmployee() {
        Employee emp = new Employee();
        Class cl = emp.getClass();//是Class，而不是class
        // /getClass获得emp对象所属的类型的对象，Class就是类的类　　　　　　　　　　
        // /Class是专门用来描述类的类，比如描述某个类有那些字段，　　　　　　　　　　
        // /方法，构造器等等！
        try {
            // /getMethod方法第一个参数指定一个需要调用的方法名称
            // /这里是Employee类的setAge方法，第二个参数是需要调用　
            // 方法的参数类型列表，是参数类型！如无参数可以指定null　
            // /该方法返回一个方法对象　
            Method sAge = cl.getMethod("setAge", new Class[]{int.class});//参数必须和方法中一样int和Integer，double和Double被视为不同的类型
            Method gAge = cl.getMethod("getAge", null);
            Method pName = cl.getMethod("printName", new Class[]{String.class});

            Object[] args1 = {new Integer(25)};
            sAge.invoke(emp, args1);

            Object[] args3 = {new String("Jack")};
            pName.invoke(emp, args3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emp;
    }
}

class Employee {
    // 定义一个员工类　　
    public Employee() {
        age = 0;
        name = null;
    }

    // 将要被调用的方法　　
    public void setAge(int a) {
        age = a;
    }

    // 将要被调用的方法　　
    public int getAge() {
        return age;
    }

    // 将要被调用的方法
    public void printName(String n) {
        name = n;
//        System.out.println("The Employee Name is: " + name);
    }

    @Override
    public String toString() {
        return "Employee name:" + name + "\t Age:" + age;
    }

    private int age;
    private String name;
}





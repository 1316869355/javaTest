package com.up72.fond;

/**
 * Created by Administrator on 2016/7/19.
 */
public class TestAB {
    public static void   main(String[] args){
        System.out.println("===new A()===");
        A a = new A();
        System.out.println("===new B()===");
        B b = new B();
        System.out.println("===new B()===");
        B b1 = new B("b1",18,true,20120101L,"Li");
        System.out.println("===new B( 4)===");
        System.out.println("===new B( 4)===" + b1.toString());

    }
}

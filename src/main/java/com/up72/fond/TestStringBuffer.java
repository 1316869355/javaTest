package com.up72.fond;

/**
 * Created by Administrator on 2016/8/24.
 */
public class TestStringBuffer {

    public static void main(String[] args){
        StringBuffer temp = new StringBuffer();

        temp.append("sdfddddddd1");
        temp.append("你好");
        temp.append("我号?");
        temp.append("大家好|");

        temp.deleteCharAt(temp.length() - 1);

        System.out.println(temp);
    }
}

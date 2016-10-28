package com.up72.pattern;

/**
 * Created by Administrator on 2016/10/25.
 */
public class ReplaceSpace {

    public static void main(String[] args){
        String content = "nihao     ";
        content = content.replaceAll("\\s+"," ");
        System.out.println(content);
    }
}

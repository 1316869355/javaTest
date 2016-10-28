package com.up72.fond.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/2.
 */
public class TestList {
    public static void main(String[] args){
        List<String> l = new ArrayList();

        l.add("kk");
        l.add(0,"kkk");
        for(String str: l){
            System.out.println(str);
        }
    }
}

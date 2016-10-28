package com.up72.fond;

import java.util.Calendar;

/**
 * Created by Administrator on 2016/8/18.
 */
public class TestCalander {
    public static void main(String[] args){
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.set(2016,8,1,0,0);
        System.out.println(c1.get(Calendar.HOUR));
        System.out.println(c1.get(Calendar.HOUR_OF_DAY));
        c2.set(2016,8,2,1,0);
        System.out.println((c2.getTimeInMillis() - c1.getTimeInMillis()));
        System.out.println((c2.getTimeInMillis() - c1.getTimeInMillis()) / (60 * 60 * 1000));
        c2.set(2016, 8, 2, 22, 0);
        System.out.println((c2.getTimeInMillis() - c1.getTimeInMillis()));
        System.out.println((c2.getTimeInMillis() - c1.getTimeInMillis()) / (60 * 60 * 1000));
        System.out.println(c1.getTimeInMillis());
        System.out.println(c2.getTimeInMillis());

    }
}

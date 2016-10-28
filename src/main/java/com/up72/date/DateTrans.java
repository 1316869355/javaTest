package com.up72.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/10/25.
 */
public class DateTrans {

    public static final SimpleDateFormat DEFAULT_FORMAT = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
    public static void main(String[] args){
        Long timestap = 1477385162484L;
        Date date = new Date(timestap);
        System.out.println(DEFAULT_FORMAT.format(date));
    }
}

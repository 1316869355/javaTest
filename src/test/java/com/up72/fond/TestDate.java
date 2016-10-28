package com.up72.fond;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.SimpleFormatter;

/**
 * Created by Administrator on 2016/9/5.
 */
public class TestDate {
    Date curDate = new Date();
    @Before
    public void initDate(){
        Calendar calendar = new GregorianCalendar();
        curDate = calendar.getTime();
        System.out.println(curDate.getTime());
    }

    @Test
    public void PrintDate(){
        System.out.print(new SimpleDateFormat("yy-MM").format(curDate));
    }
}

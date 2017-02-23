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
        System.out.println(1479115802222L);
    }

    @Test
    public void PrintDate(){
        System.out.print(new SimpleDateFormat("yy-MM-dd HH:mm").format(new Date(1479115805597L)));
    }

    @Test
    public void calMinsBetMills(){
        long diffmills =  1479106779459L- 1479104379459L;
        System.out.println(2800000 / (60 * 1000));
    }

    @Test
    public void calMinimum(){
        Calendar cal = Calendar.getInstance();
        System.out.println("timeInMills:"+cal.getTimeInMillis()+"\ttime:"+cal.getTime());
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, cal.getActualMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getActualMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getActualMinimum(Calendar.SECOND));
        System.out.println("timeInMills:"+cal.getTimeInMillis()+"\ttime:"+cal.getTime());
    }
}

package com.up72.fond;

import com.up72.common.SysCnst;

import java.util.Calendar;

/**
 * Created by Administrator on 2016/8/11.
 */
public class TimeSplit {
    private Long addTime;

    //根据时间间隔 区分时间
    public String getAddStr() {
        Long curTime = SysCnst.getCurTime();

        Calendar calendar = Calendar.getInstance();
        int yearcur = calendar.get(Calendar.YEAR);
        int monthcur = calendar.get(Calendar.MONTH) + 1;
        int daycur = calendar.get(Calendar.DATE);

        String addTimeStr = addTime.toString();

        int yearadd = Integer.parseInt(addTimeStr.substring(0, 4));
        int monthadd = Integer.parseInt(addTimeStr.substring(4, 6));
        int dayadd = Integer.parseInt(addTimeStr.substring(6, 8));

        if (yearcur > yearadd) {
            return SysCnst.getFormatTime(this.addTime).substring(0, 8);
        } else if ((monthcur - monthadd) < 1) {
            if ((daycur - dayadd) < 1) {
                Long seconds = Math.abs(curTime - addTime);
                Long mins = seconds / 60;
                Long hours = mins / 60;
                if (seconds < 300) {
                    return "刚刚";
                } else if (mins < 60) {
                    return mins + "分钟前";
                } else if (hours < 23) {
                    return hours + "天前";
                }
            } else if ((daycur - dayadd) > 1 && (daycur - dayadd) < 3) {
                return (daycur - dayadd) + "天前";
            } else {
                return SysCnst.getFormatTime(this.addTime).substring(4, 8);
            }
        }
        return SysCnst.getFormatTime(this.addTime).substring(4, 8);
    }

    public static String getShortNumberString(Integer greatCount) {
        if (null == greatCount) {
            return "";
        }
        if (greatCount > 100000000) {
            return (float) (Math.round((greatCount / 100000000.0) * 10) / 10.0) + "亿";
        } else if (greatCount > 1000000) {
            return (float) (Math.round((greatCount / 1000000.0) * 10) / 10.0) + "千万";
        } else if (greatCount > 10000) {
            return (float) (Math.round((greatCount / 10000.0) * 10) / 10.0) + "万";
        } else {
            return greatCount + "";
        }
    }

    public static void calculateRunTime() {
        Long start = System.currentTimeMillis();

        System.out.println(getShortNumberString(9000));

        Long endTime = System.currentTimeMillis();
        System.out.println("Use " + (endTime - start) + "ms");
    }

    public static void main(String[] args) {
        calculateRunTime();
    }
}

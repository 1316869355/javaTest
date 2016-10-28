//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.up72.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class SysCnst {
    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat SDF_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final Random RANDOM = new Random();
    public static final String ENCODER_KEY = "3b38e11ffd65698aedeb5ffc";
    public static final SimpleDateFormat SDF_TIME_NUM = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    public static String NO_PERMISSION = "没有访问权限";
    public static String CANT_DEL_SYSTEM_MENU = "系统菜单不允许删除";

    public SysCnst() {
    }

    public static Long getCurTime() {
        return Long.valueOf(Long.parseLong(SDF_TIME_NUM.format(new Date())));
    }

    public static String getFormatDate(Long time) {
        if(time == null) {
            return "";
        } else {
            String tmp = time.toString();
            return tmp.length() != 17?"":tmp.substring(0, 4) + "-" + tmp.substring(4, 6) + "-" + tmp.substring(6, 8);
        }
    }

    public static String getFormatTime(Long time) {
        if(time == null) {
            return "";
        } else {
            String tmp = time.toString();
            return tmp.length() != 17?"":tmp.substring(0, 4) + "-" + tmp.substring(4, 6) + "-" + tmp.substring(6, 8) + " " + tmp.substring(8, 10) + ":" + tmp.substring(10, 12) + ":" + tmp.substring(12, 14);
        }
    }

    public static class LoginCnst {
        public static final String USER_NAME_OR_PWD_ERROR = "用户名或密码错误";
        public static final String USER_NAME_OR_PWD_BLANK = "用户名或密码为空";
        public static final String KAPTCHA_ERROR = "验证码不正确";
        public static final String USER_NOT_EXISTS = "用户不存在";
        public static final String USER_IS_DISABLE = "用户已禁用，请联系管理员";
        public static final String USER_IS_DEL = "用户已删除，请联系管理员";
        public static final String PWD_ERROR = "密码错误";
        public static final String LOGIN_SUCCESS = "登录成功";
        public static final String SYSTEM_ERROR = "系统异常，请联系管理员";
        public static final String PWD_ERROR_OVER_TIMES = "密码错误超过{0}次";
        public static final String LOGIN_TRY_TIMES_KEY = "loginTryTimes";
        public static int ALLOW_TRY_TIMES_BEFORE_KAPTCHA = 3;
        public static int ALLOW_TRY_TIMES_BEFORE_LOCK_USER = 10;
        public static boolean IS_LOCK_USER = false;

        public LoginCnst() {
        }
    }

    public static class MenuCnst {
        public static final int MENU_TOP = 0;
        public static final int MENU_LEFT = 1;
        public static final int MENU_NAV = 2;
        public static final int OP_TYPE_MENU = 0;
        public static final int OP_TYPE_OPERA = 1;
        public static final Map<Integer, String> OP_TYPE_MAP = new LinkedHashMap() {
            {
                this.put(Integer.valueOf(0), "菜单型");
                this.put(Integer.valueOf(1), "操作型");
            }
        };

        public MenuCnst() {
        }
    }

    public static class UserCnst {
        public static final int GENDER_FEMALE = 0;
        public static final int GENDER_MALE = 1;
        public static final int GENDER_SECRET = 2;
        public static final String[] GENDER_NAME_ARR = new String[]{"女", "男", "保密"};
        public static final Map<Integer, String> GENDER_MAP_ID_NAME = new LinkedHashMap() {
            {
                this.put(Integer.valueOf(0), SysCnst.UserCnst.GENDER_NAME_ARR[0]);
                this.put(Integer.valueOf(1), SysCnst.UserCnst.GENDER_NAME_ARR[1]);
                this.put(Integer.valueOf(2), SysCnst.UserCnst.GENDER_NAME_ARR[2]);
            }
        };
        public static final Map<String, Integer> GENDER_MAP_NAME_ID = new LinkedHashMap() {
            {
                this.put(SysCnst.UserCnst.GENDER_NAME_ARR[0], Integer.valueOf(0));
                this.put(SysCnst.UserCnst.GENDER_NAME_ARR[1], Integer.valueOf(1));
                this.put(SysCnst.UserCnst.GENDER_NAME_ARR[2], Integer.valueOf(2));
            }
        };

        public UserCnst() {
        }
    }
}

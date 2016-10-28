package com.up72.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/8/24.
 */
public class OneTest {
    public static void main(String[] args) {
        Pattern ptn = Pattern.compile("@(\\S+?)[\\s]");
        String str = "@张三@李四 234234 @王五 23232 @找刘";
        String temp = str.replace("@", " @") + " ";
        Matcher matcher = ptn.matcher(temp);
        while (matcher.find()) {
            System.out.println(matcher.group(1));
        }
    }
}

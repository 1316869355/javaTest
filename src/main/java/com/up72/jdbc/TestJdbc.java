package com.up72.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Administrator on 2016/7/19.
 */
public class TestJdbc {
    public static Connection connection;
    public static Map<String,String> conf = new HashMap<String, String>();
    static{
        getConfig();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(conf.get("url"),conf.get("username"),conf.get("password"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("找不到Driver");
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    public TestJdbc(){

    }
    private static Map<String,String> getConfig(){
        Properties properties = new Properties();
        conf.put("url","");
        conf.put("username","");
        conf.put("password","");

        try {
            properties.load(TestJdbc.class.getResourceAsStream("/jdbc.properties"));
//            System.out.println(properties.get("jdbc.url"));
            conf.put("url", properties.get("jdbc.url").toString());
            conf.put("username",properties.get("jdbc.username").toString());
            conf.put("username",properties.get("jdbc.password").toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.print("get Nothing");
        }
        return conf;
    }
    public static void main(String[] args){
//        new TestJdbc();
    }
}

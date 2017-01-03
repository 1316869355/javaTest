package cn.nn.jdbc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Administrator on 2016/7/19.
 */
public class TestJdbc {
    static Log log = LogFactory.getLog(TestJdbc.class);
    public static Connection connection;
    final static Map<String, String> conf = new HashMap<String, String>();

    static {
        getConfig();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(conf.get("url"), conf.get("username"), conf.get("password"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("找不到Driver");
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public TestJdbc() {

    }

    private static Map<String, String> getConfig() {
        Properties properties = new Properties();
        conf.put("url", "");
        conf.put("username", "");
        conf.put("password", "");
        InputStream inputStream = null;
        try {
            String path = Class.class.getResource("/").getPath();
            inputStream = new FileInputStream(path+"jdbc.properties");
            properties.load(inputStream);
//            System.out.println(properties.get("jdbc.url"));
            conf.put("url", properties.get("jdbc.url").toString());
            conf.put("username", properties.get("jdbc.username").toString());
            conf.put("username", properties.get("jdbc.password").toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.print("get Nothing");
        }finally {
            if(null != inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return conf;
    }

    public static void closeDbCnn(Connection conn) {
        if(null != conn){

            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                log.error("关闭数据库连接出错");
            }
        }
    }

    public static void main(String[] args) {
//        new TestJdbc();
    }
}

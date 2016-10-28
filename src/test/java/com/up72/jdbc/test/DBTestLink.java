package com.up72.jdbc.test;
import com.up72.jdbc.TestJdbc;
import org.junit.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Administrator on 2016/8/26.
 */
public class DBTestLink {

    @Test
    public void save(){
        try {
        Statement statement = TestJdbc.connection.createStatement();
            String sql = "SELECT * FROM person";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()){
                System.out.print(rs.getString(1)+"\t");
                System.out.println(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

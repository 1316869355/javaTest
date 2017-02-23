package cn.nn.io.excel;

import org.junit.Test;

import java.io.IOException;

/**
 * Created by Administrator on 2016/11/8.
 */
public class TestPrimaryPoi {
    @Test
    public void testReadExcel(){
        try {
            PrimaryPoi.readExcel("C:\\Users\\Administrator\\Desktop\\球场录入信息注释.xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

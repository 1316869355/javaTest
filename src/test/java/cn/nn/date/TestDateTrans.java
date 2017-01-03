package cn.nn.date;

import org.junit.Test;

import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/10/25.
 */
public class TestDateTrans {

    public static final SimpleDateFormat DEFAULT_FORMAT = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
    public static void main(String[] args){
        Long timestap = 1477385162484L;
        Date date = new Date(timestap);
        System.out.println(DEFAULT_FORMAT.format(date));
    }

    @Test
    public void test(){
        Long a = 290L;
        Long b = 290L;
        Integer x = 910;
        Integer y = 90;
        System.out.println();
        if(a > 0){
            System.out.println("a > 0");
        }
        if(b > a){
            System.out.println("b > a");
        }
    }
}

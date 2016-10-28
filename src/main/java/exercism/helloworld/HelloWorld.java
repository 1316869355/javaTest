package exercism.helloworld;

/**
 * Created by Administrator on 2016/10/25.
 */
public class HelloWorld {
    public static String hello(String obj){
        if(null != obj && !obj.isEmpty()){
            return "Hello, "+obj+"!";
        }
        return "Hello, World!";
    }
}

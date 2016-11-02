package cn.nn.thread;

/**
 * Created by Administrator on 2016/8/10.
 */
public class SellTicketClient {
    public static void main(String[] args) {

        SellTicketRunnable str = new SellTicketRunnable();
        Thread t1 = new Thread(str, "A售票口");
        Thread t2 = new Thread(str, "B售票口");
        Thread t3 = new Thread(str, "C售票口");
        Thread t4 = new Thread(str, "D售票口");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
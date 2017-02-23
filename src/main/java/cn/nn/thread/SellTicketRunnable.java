package cn.nn.thread;

/**
 * Created by Administrator on 2016/8/10.
 */
public class SellTicketRunnable implements Runnable{

    private int ticket = 100;   //票的数量
    private Object obj = new Object();  //同步锁对象，必须为同一锁对象

    public void run() {
        while (true) {
            synchronized (this) {
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName()+"正在售第"+ticket--+"张票");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
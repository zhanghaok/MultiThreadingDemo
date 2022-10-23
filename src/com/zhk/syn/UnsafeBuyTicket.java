package com.zhk.syn;

/**
 * 不安全的买票
 * 线程不安全，有负数
 */
public class UnsafeBuyTicket {

    public static void main(String[] args) {
        BuyTicket buyTicket = new BuyTicket();

        new Thread(buyTicket,"苦逼的我").start();
        new Thread(buyTicket,"牛逼的你").start();
        new Thread(buyTicket,"可恶的黄牛党").start();
    }


}

class BuyTicket implements Runnable{
    //票
    private int tickiNum = 10;
    boolean flag = true;

    @Override
    public void run() {
        //买票
        while (flag){
            buy();
        }

    }
    //synchronized 同步方法，锁的是this
    private synchronized void buy(){
        //判断是否有票
        if (tickiNum<=0){
            flag = false;
            return;
        }
        //模拟延时
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //买票
        System.out.println(Thread.currentThread().getName()+"==>拿到票"+tickiNum--);

    }
}

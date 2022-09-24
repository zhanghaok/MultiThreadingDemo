package com.zhk.state;

/**
 * 测试jion方法、、想象为插队
 */
public class TestJoin implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("线程VIP来了");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestJoin testJoin = new TestJoin();
        Thread thread = new Thread(testJoin);
        thread.start();

        //主线程
        for (int i = 0; i < 1000; i++) {
            if (i==200){
                thread.join();//插队
            }
            System.out.println("main"+i);
        }
    }
}

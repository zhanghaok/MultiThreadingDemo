package com.zhk.state;

import com.zhk.demo1.TestThread4;

/**
 * 模拟网络延时：放大问题的发生性（下面的例子中展示了，通过延时发现多个线程抢夺一个资源时候，发现了数据紊乱的情况）
 */
public class TestSleep implements Runnable{
    private int trickNums = 10;

    @Override
    public void run() {
        while (true){
            if (trickNums<=0){
                break;
            }

            //模拟延时
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName()+"--->拿到了第"+trickNums--+"张票");
        }
    }

    public static void main(String[] args) {

        //一份资源
        TestThread4 trick = new TestThread4();

        new Thread(trick,"小明").start();
        new Thread(trick,"小王").start();
        new Thread(trick,"小张").start();
    }
}

package com.zhk.demo1;

//多个线程同时操作一个对象
//买火车票的例子

//发现问题：多个线程操作同一个资源的情况下，线程不安全，数据紊乱。
public class TestThread4 implements Runnable{

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

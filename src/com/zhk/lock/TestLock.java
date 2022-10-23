package com.zhk.lock;

import java.util.concurrent.locks.ReentrantLock;

//测试Lock锁
public class TestLock {
    public static void main(String[] args) {
        TestLock2 lock2 = new TestLock2();

        new Thread(lock2).start();
        new Thread(lock2).start();
        new Thread(lock2).start();
    }
}
//买票的例子
class TestLock2 implements Runnable{
    int trickNums = 10;

    //定义Lock锁
    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true){
            try {
                lock.lock();//加锁
                if (trickNums>0){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(trickNums--);
                }else {
                    break;
                }
            } finally {
                lock.unlock();//解锁
            }

        }
    }
}

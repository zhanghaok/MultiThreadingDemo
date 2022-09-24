package com.zhk.state;

//测试停止线程
//1.建议线程正常停止-->利用次数，不建议死循环
//2.建议使用标注位
//3.不要使用stop或destory过时或JDK不建议的方法
public class TestStop implements Runnable{

    //设置一个标志位
    private boolean flag = true;

    @Override
    public void run() {
        int i =0;
        while (flag){
            System.out.println("running...Thread"+i++);
        }
    }

    //2.设置一个公开的方法停止线程,转换标志位
    public void stop(){
        this.flag = false;
    }

    public static void main(String[] args) {
        TestStop testStop = new TestStop();
        new Thread(testStop).start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("main"+i);
            if (i==900){
                //调用stop方法停止线程
                testStop.stop();
                System.out.println("线程停止了");
            }
        }
    }
}

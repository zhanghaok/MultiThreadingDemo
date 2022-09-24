package com.zhk.state;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 模拟倒计时
 */
public class TestSleep2 {

    public static void main(String[] args) {
        //打印当前时间
        Date startTime = new Date(System.currentTimeMillis());//获取系统当前时间

        while (true){
            try {
                Thread.sleep(1000);
                System.out.println(new SimpleDateFormat("HH:mm:ss").format(startTime));
                //更新时间
                startTime = new Date(System.currentTimeMillis());////更新当前时间
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void tenDown(){
        int num = 10;
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(num--);
            if (num<=0){
                break;
            }
        }

    }
}

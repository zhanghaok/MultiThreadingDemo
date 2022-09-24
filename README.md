# Multithreading

> 课程链接：https://www.bilibili.com/video/BV1V4411p7EF?p=1&vd_source=b67ce643ed65f6136a48fb03481df142



多线程demo

## 01线程简介

任务，进程，线程，多线程



**多任务**：

![image-20220924134311851](./imgs/image-20220924134311851.png)

**多线程：**

![image-20220924134511518](./imgs/image-20220924134511518.png)

**普通方法调用和多线程：**

![image-20220924134612805](./imgs/image-20220924134612805.png)

**程序、进程、线程**

![image-20220924134729947](./imgs/image-20220924134729947.png)

**Process与Thread**

![image-20220924134840519](./imgs/image-20220924134840519.png)

**本节核心概念**

![image-20220924134940467](./imgs/image-20220924134940467.png)

## 02 线程的创建

main线程，gc线程

main线程是用户自己写的

gc线程是Java虚拟机创建的

**三种创建方式**

![image-20220924135057344](./imgs/image-20220924135057344.png)

**Thread**

![image-20220924135218202](./imgs/image-20220924135218202.png)

代码：

```java
package demo01;

//创建线程方式一：继承Thread类,重写run方法，调研start开启线程
public class TestThread1 extends Thread{

    @Override
    public void run() {
        //run方法线程体
        for (int i=0;i<20;i++){
            System.out.println("我在看代码---"+i);
        }
    }

    public static void main(String[] args) {
        //main线程，主线程
        
        //创建一个线程对象
        TestThread1 thread1 = new TestThread1();
        //调用start方法,开启线程
        thread1.start();

        for (int i = 0; i < 2000; i++) {
            System.out.println("我在学习多线程---"+i);
        }
    }
}
```

运行结果：

![image-20220924142641499](./imgs/image-20220924142641499.png)

**使用线程下载图片**

代码：

```java
package com.zhk.demo1;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

//练习Thread，多线程实现下载图片
public class TestThread2 extends Thread{

    private String url;
    private String name;
    public TestThread2(String url,String name){
        this.url = url;
        this.name = name;
    }

    //下载图片线程的执行体
    @Override
    public void run() {
        //线程体
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url,name);
        System.out.println("文件下载成功。"+name);
    }

    public static void main(String[] args) {
        TestThread2 t1 = new TestThread2("https://pic3.zhimg.com/v2-6f474edfce5f6a065baf944f11410c68_r.jpg","E:/imgs/1.jpg");
        TestThread2 t2 = new TestThread2("https://pic2.zhimg.com/v2-6d9fcfa07d4dfbbc75f586c2fbfb0811_r.jpg","E:/imgs/2.jpg");
        TestThread2 t3 = new TestThread2("https://pic3.zhimg.com/v2-01d5a2c2a3fcf99a35fa1c76ac7ed612_r.jpg","E:/imgs/3.jpg");

        t1.start();
        t2.start();
        t3.start();

    }
}

//下载器
class WebDownloader{
    //下载方法
    public void downloader(String url,String name){
        try {
            FileUtils.copyURLToFile(new URL(url),new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO异常，downloader方法出问题了");
        }
    }

}
```

结果：

可以发现顺序不一样，说明线程是同时执行的。

![image-20220924160826669](./imgs/image-20220924160826669.png)

**Runable**

![image-20220924161142427](./imgs/image-20220924161142427.png)

代码：

```java
package com.zhk.demo1;

//线程实现方法2：实现Runnable接口，实现run方法，执行线程需要丢入runnable实现类，调用strat方法
public class TestThread3 implements Runnable{
    @Override
    public void run() {
        //run方法线程体
        for (int i=0;i<20;i++){
            System.out.println("我在看代码---"+i);
        }
    }

    public static void main(String[] args) {
        //main线程，主线程

        //创建一个Runnable接口实现类对象
        TestThread3 testThread3 = new TestThread3();
        //创建线程对象，通过线程对象来开启我们的线程，代理
//        Thread thread = new Thread(testThread3);
//        //调用start方法,开启线程
//        thread.start();

        new Thread(testThread3).start();


        for (int i = 0; i < 2000; i++) {
            System.out.println("我在学习多线程---"+i);
        }
    }

}
```

运行结果：

![image-20220924162721689](./imgs/image-20220924162721689.png)

**小节**

![image-20220924163137936](./imgs/image-20220924163137936.png)

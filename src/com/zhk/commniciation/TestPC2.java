package com.zhk.commniciation;

//测试生产者消费者问题--> 利用标志位解决（信号灯法）
public class TestPC2 {
    public static void main(String[] args) {
        TV tv = new TV();
        new Player(tv).start();
        new Watcher(tv).start();
    }
}

//生产者--》演员
class Player extends Thread{
    TV tv;
    public Player(TV tv){
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (i%2==0){
                this.tv.play("快乐大本营播放中");
            } else {
                this.tv.play("抖音：记录美好生活");
            }
        }
    }
}
//消费者==》观众
class Watcher extends Thread{
    TV tv;
    public Watcher(TV tv){
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            this.tv.watch();
        }
    }
}
//产品（资源）==》节目
class TV{
    //演员表演，观众等待 true
    //观众观看，演员等待 false
    String voice;//表演的节目
    boolean flag = true;//什么时候演员表演，什么时候观众观看

    //表演
    public synchronized void play(String voice){
        if (!flag){//演员等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("演员表演了："+voice);
        //通知观众观看
        this.notifyAll();
        this.voice = voice;
        this.flag=!flag;
    }
    //观看
    public synchronized void watch(){
        if (flag){//观众等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("观众观看了："+voice);
        //通知演员表演
        this.notifyAll();
        this.flag=!flag;
    }
}
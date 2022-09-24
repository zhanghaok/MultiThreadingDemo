package com.zhk.state;

/**
 * 测试守护线程
 * 上帝守护你
 */
public class TestDaemon {
    public static void main(String[] args) {
        God god = new God();
        You you = new You();

        Thread thread = new Thread(god);
        thread.setDaemon(true);//设置为守护线程
        thread.start();

        new Thread(you).start();//用户线程
    }

}

//上帝
class God implements Runnable{
    @Override
    public void run() {
        while (true){
            System.out.println("上帝守护你");
        }
    }
}

//你
class You implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 36500; i++) {
            System.out.println("你一生都开心的活着");
        }
        System.out.println("-=====Good bye! World!=====-");
    }
}

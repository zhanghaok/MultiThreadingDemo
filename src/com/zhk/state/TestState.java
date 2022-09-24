package com.zhk.state;

/**
 * 观察测试现成的状态
 */
public class TestState {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("/////");
            }
        });
        
        //观察状态
        Thread.State state = thread.getState();
        System.out.println(state);//NEW

        //观察启动后
        thread.start();//启动线程
        state = thread.getState();
        System.out.println(state);//RUN

        while (state!= Thread.State.TERMINATED){
            thread.sleep(100);
            state = thread.getState();
            System.out.println(state);//输出状态
        }



    }
}

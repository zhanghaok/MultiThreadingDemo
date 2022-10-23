package com.zhk.commniciation;

//测试 生产者消费者问题--> 利用缓冲区解决（管程法）

//生产者、消费者、产品、缓冲区

public class TestPC {
    public static void main(String[] args) {
        Container container = new Container();

        new Productor(container).start();
        new Consumer(container).start();
    }
}

//生产者
class Productor extends Thread{
    Container container;
    public Productor(Container container){
        this.container = container;
    }

    //生产产品
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("生产了-->"+i+"只鸡");
            container.push(new Chicken(i));
        }
    }
}
//消费者
class Consumer extends Thread{
    Container container;
    public Consumer(Container container){
        this.container = container;
    }

    //消费产品
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("消费了-->"+container.pop().id+"只鸡");
        }
    }
}
class Chicken{
    int id;//产品编号

    public Chicken(int id) {
        this.id = id;
    }
}

class Container{
    //需要一个容器大小
    Chicken[] chickens = new Chicken[10];
    //容器计数器
    int counter = 0;

    //生产者生产产品
    public synchronized void push(Chicken chicken){
        //如果容器满了，就需要等待消费者消费
        if (counter==chickens.length){
            //生产等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        //如果没有满，我们就需要丢入产品
        chickens[counter] = chicken;
        counter++;

        //可以通知消费者消费了
        this.notifyAll();
    }

    //消费者消费产品
    public synchronized Chicken pop(){
        //判断能否消费
        if (counter==0){
            //等待生产者生产，消费者消费
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        //如果可消费
        counter--;
        Chicken chicken = chickens[counter];

        //吃完了，通知生产者生产
        this.notifyAll();
        return chicken;
    }

}

package com.zhk.syn;

/**
 * 不安全的取钱
 * 两个人去账户取钱，账户
 */
public class UnsafeBank {
    public static void main(String[] args) {
        Account account = new Account(1000,"结婚基金");


        Drawing you = new Drawing(account,50,"你");
        Drawing girlfriend = new Drawing(account,100,"girlFriend");

        you.start();
        girlfriend.start();
    }
}

//账户
class Account{
    int money=0;//余额
    String name;//卡名

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}
//银行：模拟取款
class Drawing extends Thread{
    Account account;//账户
    //去了多少钱
    int drawingMoney;
    //现在手里还有多少钱
    int nowMoney;
    public Drawing(Account account,int drawingMoney,String name){
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    //synchronized 默认锁的是this 可以发现这个加了synchronized锁不了，因为我们实际操作的对象是银行账户啊！所以需要synchronized块（同步块）
    @Override
    public synchronized void run() {
        //锁的对象就是变化的量，增删改
        synchronized (account) {
            //判断有没有钱
            if (account.money-drawingMoney<0){
                System.out.println(Thread.currentThread().getName()+"钱不够");
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            account.money = account.money - drawingMoney;
            nowMoney = nowMoney + drawingMoney;

            System.out.println(account.name+"余额为："+account.money);
            System.out.println(this.getName()+"手里的钱："+nowMoney);
        }

    }
}

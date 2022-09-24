package com.zhk.demo2;

//静态代理模式总结
//真实对象和代理对象都有实现同一个接口
//代理对象要代理真实角色

//好处：代理对象可以做很多真实对象做不了的事情，真实对象专注自己的事情


public class StaticProxy {

    public static void main(String[] args) {

        You you = new You();//你要结婚
//        WeddingCompany weddingCompany = new WeddingCompany(you);
//        weddingCompany.HappyMarry();

        //对比下面2句话，体会线程的静态代理
        new Thread(()-> System.out.println("我爱你")).start();
        new WeddingCompany(new You()).HappyMarry();
    }
}

interface Marry{
    void HappyMarry();
}

//真实角色
class You implements Marry{

    @Override
    public void HappyMarry() {
        System.out.println("狗子要结婚了，超开心");
    }
}

//代理角色

class WeddingCompany implements Marry{
    //代理谁-->真实目标角色
    private Marry target;
    public WeddingCompany(Marry target){
        this.target = target;
    }

    @Override
    public void HappyMarry() {
        before();
        this.target.HappyMarry();//这就是真实角色
        after();
    }

    private void after() {
        System.out.println("结婚之后收尾款");
    }

    private void before() {
        System.out.println("结婚之前布置现场");
    }
}
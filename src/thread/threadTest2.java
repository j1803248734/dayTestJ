package thread;

import java.util.concurrent.locks.ReentrantLock;


public class threadTest2 {

    private static String fileA = "A文件";
    private static String filedB = "B文件";

    public static void main(String[] args) {
//        Account a1 = new Account(0);
//
//        Customer c1 = new Customer(a1);
//        Customer c2 = new Customer(a1);
//
//        c1.setName("甲");
//        c2.setName("乙");
//
//        c1.start();
//        c2.start();

        diedLock();

    }

    /**
     * 模拟死锁  A - > B     B - > A
     * 采用专门的算法，多个线程之间规定先后执行的顺序，规避死锁问题
     * 避免锁的嵌套
     * 减少同步共享变量
     */
    static void diedLock(){
        new Thread(){
            @Override
            public void run() {

                synchronized (fileA){
                    try {
                        sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(this.getName() + ":文件A写入");
                    synchronized (filedB){
                        System.out.println(this.getName() + ":文件B写入");
                    }
                    System.out.println(this.getName() + ":所有文件保存");

                }

            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                synchronized (filedB){
                    System.out.println(this.getName() + ":文件B写入");
                    synchronized (fileA){
                        System.out.println(this.getName() + ":文件A写入");
                    }
                    System.out.println(this.getName() + ":所有文件保存");

                }
            }
        }.start();

    }
}



class Account {

    private double balance;
    ReentrantLock lock = new ReentrantLock(true);

    public Account(double balance){
        this.balance = balance;
    }
    /**
     * 存钱
     */
    public void deposit(double val){
        if(val > 0){
            lock.lock();
            balance += val;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"存钱成功，余额为："+balance);
            lock.unlock();
        }
    }
}

class Customer extends Thread{
    private Account acc;

    public Customer(Account acc){
        this.acc = acc;
    }

    @Override
    public void run() {
        for (int i = 0;i<3;i++){
            acc.deposit(1000);
        }
    }
}
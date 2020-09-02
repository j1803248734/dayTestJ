package thread;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class taskRunable {

    static window1 w1 =  null;

    public static window1 getW1() {
        if(w1 == null){
            w1 = new window1();
        }
        return w1;
    }

    public static void main(String[] args) {

        Thread t1 = new Thread(getW1());
        Thread t2 = new Thread(getW1());

        t1.start();
        t2.start();
//        window2 w2 = new window2();
//
//        //通过futureTask对象的get方法来接收futureTask的值
//        FutureTask ft = new FutureTask(w2);
//
//        Thread t1 = new Thread(ft);
//        t1.setName("线程1");
//        t1.start();
//
//        try {
//            Object sum = ft.get();
//            System.out.println(Thread.currentThread().getName()+"  sum  :"+sum);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        ExecutorService es = Executors.newFixedThreadPool(5);
//        es.submit(new window1());
//        es.submit(new window1());
//        es.submit(new window1());
//        es.submit(new window1());
//        es.submit(new window1());
//        es.shutdown();
//        windowLock wl = new windowLock();
//        Thread t1 = new Thread(wl);
//        Thread t2 = new Thread(wl);
//        Thread t3 = new Thread(wl);
//
//        t1.setName("窗口1");
//        t2.setName("窗口2");
//        t3.setName("窗口3");
//
//        t1.start();
//        t2.start();
//        t3.start();
    }
}





class window1 implements Runnable{
    private static int ticket = 100;
    @Override
    public void run() {
        while(true){
            synchronized (this){
                if(ticket > 0){
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " : " +ticket);
                    ticket--;
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    break;
                }
            }
        }
    }
}

class window2 implements Callable{
    private int sum=0;//
    @Override
    public Object call() throws Exception {
        for(int i = 0 ; i <= 100 ; ++i){
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName()+":"+i);
                sum += i;
            }
        }
        return sum;
    }
}

class windowLock implements Runnable{
    static int ticket = 100;
    ReentrantLock lock = new ReentrantLock(true);
    @Override
    public void run() {
        while(true){
            //锁定
            lock.lock();
            if(ticket > 0){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + "售出第" + ticket + "张票");
                ticket--;
                lock.unlock();

            }else{
                break;
            }
        }
    }
}

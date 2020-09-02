package thread;

public class communicationTest {
    /**
     * 消费生产者问题
     */
    public static void main(String[] args) {
        Factory factory = new Factory();
        Producer p1 = new Producer(factory);
        p1.setName("生产者1");


        Consumer c1 = new Consumer(factory);
        c1.setName("消费者1");
        Consumer c2 = new Consumer(factory);
        c1.setName("消费者2");
//        Consumer c3 = new Consumer(factory);
//        c1.setName("消费者3");

        p1.start();
        c1.start();
        c2.start();
//        c3.start();
    }
}

class Factory{
    private static int productCount = 0;
//        synchronized  代码块 当前线程修饰的不是当前对象的话  会报IllegalMonitorStateException
      synchronized void  produceProduct(){
        /**
         * 异步锁
         */

            if(productCount < 50){


//                    try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                    productCount++;
                    System.out.println(Thread.currentThread().getName()+":开始生产第"+productCount+"个产品");
                    notify();

            }else{
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

    }

    synchronized void consumeProduct(){
            if(productCount > 0){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+":开始消费第"+productCount+"个产品");
                productCount--;
                notify();
            }else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

    }
}

/**
 * 生产者
 */
class Producer  extends Thread{
    private Factory factory;
    public Producer(Factory factory){
        this.factory = factory;
    }

    @Override
    public void run() {
        try {
            sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+";开始生产产品......");
        while(true){
            factory.produceProduct();
        }
    }
}

class Consumer extends Thread{
    private Factory factory;
    public Consumer(Factory factory){
        this.factory = factory;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+":开始消费产品");
        while(true){
            factory.consumeProduct();
        }
    }
}
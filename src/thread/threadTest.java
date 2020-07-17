package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 多线程的各种继承
 */
public class threadTest {


    static class RunableTask implements Runnable{

        @Override
        public void run() {
            System.out.println(" hello world !");
        }
    }

    static class RunableTask2 extends Thread  {
        public void run() {
            System.out.println("hello world");
        }
    }

    static class Callable1 implements Callable<String>{

        @Override
        public String call() throws Exception {
            return "hello worldd";
        }
    }




    private static final long count = 1000000001L;

    public static void main(String[] args) throws InterruptedException {
        RunableTask runableTask = new RunableTask();
        new Thread(runableTask).start();
        new RunableTask2().start();
        //----------------------------------
        Callable1 callable1 = new Callable1();
        FutureTask<String> futureTask = new FutureTask<>(callable1);
        new Thread(futureTask).start();
        try {
            String res = futureTask.get();
            System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
        concurrency();
        serial();
    }


    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();

                for(int j = 0 ; j < 1 ; ++j){
                    synchronized (threadTest.class){
                        Thread thread = new Thread(()->{
                            for(long i = 0 ; i < count ; ++i){
                                long a = 0;
                                a += 5L;
//                        a.addAndGet(5L);
                            }
                        });
                        thread.start();
                        long b = 0;
                        for (long i = 0; i < count; i++) {
                            b--;
                        }
                        //等待线程死亡
                        thread.join();
                    }
                }


        long time = System.currentTimeMillis() - start;
        System.out.println("Concurrency : " + time + "ms, b = " );
    }

    protected static void serial(){
        long start = System.currentTimeMillis();
        long a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }
        long b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("serial : " + time + "ms, b = " + b + ", a = " + a);
    }


}

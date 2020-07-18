package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 多线程实现计数
 */
public class CountExample {
    // 请求数
    public static int clientTotal = 5000;
    // 线程数
    public static int threadTotal = 200;

    public static int count = 0;

    public static void main(String[] args) {
        ce1();
    }

    static void ce1(){
        //线程池 + 信号模拟
        //新建线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //定义信号量
        final Semaphore semaphore = new Semaphore(threadTotal);
        for(int i = 0 ; i < clientTotal ; ++i){
            executorService.execute(()->{
                try {
//                    从此信号量获取许可，阻止直到有一个，或者线程为 interrupted 。

                       semaphore.acquire();
                       add();
//                    发布许可证，将其返回到信号量。
                       semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        }
        executorService.shutdown();
        System.out.println(count);
    }

    static void add(){
        count++;

    }

    static void ce2(){

    }
}

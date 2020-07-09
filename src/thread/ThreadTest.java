package thread;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ThreadTest {
    private int value;

    protected int getNext(){
        return value++;
    }

    public static void main(String[] args) {
        ThreadTest threadTest = new ThreadTest();
        Executor executors = Executors.newFixedThreadPool(8);
        int num = 0;
        for(int i = 0 ; i < 200 ; i++){
            executors.execute(()-> System.out.println(threadTest.getNext()));
            num++;
        }
        System.out.println("num"+num);

    }
}

package thread;

/**
 * 死锁模拟
 */
public class deadlock {
    private static String A = "A";
    private static String B = "B";

    public static void main(String[] args) {
        new deadlock().deadlock();
    }

    private void deadlock(){
        Thread thread = new Thread(()->{
            synchronized(A){
                try{
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (B) {
                    System.out.println("1");
                }
            }
        });
        Thread thread1 = new Thread(()->{
           synchronized (B){
               synchronized (A){
                   System.out.println("2");
               }
           }
        });
        thread.start();
        thread1.start();
    }
}

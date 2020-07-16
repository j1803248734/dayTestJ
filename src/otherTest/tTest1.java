package otherTest;

public  class tTest1 implements Runnable{
    private  static volatile   int count = 0;
    public  static  void main(String[] args) {
        for(int i = 0 ; i < 10 ; ++i){
            Thread thread = new Thread(new tTest1());
            thread.start();
        }
        try{
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("result: " + count);
    }

    @Override
    public  void run() {
        synchronized(tTest1.class){
            for (int i = 0; i < 1000000; i++)
                count++;
            }
        }
    }


    class demo1{
        public static void main(String[] args) {
            synchronized (demo1.class){

            }
        }
        public static void method(){};
    }


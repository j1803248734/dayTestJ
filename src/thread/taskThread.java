package thread;

public class taskThread extends Thread{
    //卖票问题:  三个窗口卖票 总数100张 使用继承自Thread方式

    public static void main(String[] args) {
        window w1 = new window();
        window w2 = new window();
        window w3 = new window();

        w1.setName("one");
        w2.setName("two");
        w3.setName("three");

        w1.start();
        w2.start();
        w3.start();
    }



}

class window extends Thread{
    //静态块
    static  int  ticket = 100;

    @Override
    public  void run() {
        while(true){
            synchronized (""){
                if(ticket > 0){
                    try {
                        sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(getName() + "当前售出" +ticket+"张票");
                    ticket--;
                }else{
                    break;
                }
            }
        }
    }
}
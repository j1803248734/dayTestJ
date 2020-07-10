package thread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HandlerThread implements Runnable{
    private Socket socket;
    public HandlerThread(Socket client){
        socket = client;
        /**
         * 此处this的用法   因为HandlerThread实现了Runnable接口
         * 所以每个HandlerThread 都相当于一个new Runnable()
         */
        new Thread(this).start();
    }
    @Override
    public void run() {
        DataInputStream dis = null;
        DataOutputStream dos = null;
        try{
            dis = new DataInputStream(socket.getInputStream());
            //输入流内容
            String clientInputdir = new String(dis.readUTF().getBytes("ISO-8859-1"),"utf-8");
            System.out.println("client send text "+clientInputdir);
            dos = new DataOutputStream(socket.getOutputStream());
            System.out.println(" please input :\t");
            String s = "one";
            dos.writeUTF(s);

                dos.close();
                dis.close();
        } catch (IOException e) {
            System.out.println("服务器 run 异常: " + e.getMessage());
        }finally {
            try {
                assert socket!=null;
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}

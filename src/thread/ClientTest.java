package thread;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

public class ClientTest {
    private static Logger logger = Logger.getLogger(ClientTest.class);
    public static void main(String[] args) throws InterruptedException {
//        ServerTest.server();

//        int i = 0;
//        for(; i < 2000 ; i++){
            client();
//        }
    }
    public static void client(){
        logger.info("client started ...");
        //新建缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);
        SocketChannel socketChannel = null;
        try {
            socketChannel  = SocketChannel.open();
            /**
             * 配置它的阻塞行为，以实现非阻塞式的信道。异步
             */
            socketChannel.configureBlocking(false);
            //建立连接
            socketChannel.connect(new InetSocketAddress("localhost",8080));
            /**
             * 确认有没有完成连接  之前有连接或者连接完成都返回true  ，  如果连接还没有完成返回false ,主动抛异常
             */
            if(socketChannel.finishConnect()){
                int i = 0;
//                while(true){
                    TimeUnit.SECONDS.sleep(1);
                    String info = "I'm "+(i++)+"-th information from client";
                    buf.clear();
                    buf.put(info.getBytes());
                    /**
                     * 切换为读模式
                     */
                    buf.flip();
                    /**
                     * hasRemaining判断是否还有未读元素
                     */
                    while(buf.hasRemaining()){
                        System.out.println(buf);
                        socketChannel.write(buf);
                    }
                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                assert socketChannel != null;
                socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

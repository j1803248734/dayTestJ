package http.http1;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;


/**
 *
 */
public class ServerTest {
    private final static int port = 8080;

    private static Logger logger = Logger.getLogger(ServerTest.class);

    private int value;

    protected int getNext(){
        return value++;
    }

    public static void main(String[] args) {
//        ThreadTest threadTest = new ThreadTest();
//        Executor executors = Executors.newFixedThreadPool(8);
//        int num = 0;
//        for(int i = 0 ; i < 200 ; i++){
//            executors.execute(()-> System.out.println(threadTest.getNext()));
//            num++;
//        }
//        System.out.println("num"+num);
//        new Thread(()->{
//            System.out.println("1");
//        });
//        IOTest();
//        FileNIOTest();
//        server();
//        client();
//        serverThread(port);
    }

    public static void IOTest(){

        /**
         * stream是单向的
         */
        InputStream in = null;
        try {
            /**
             * 缓冲输入流
             */
            in = new BufferedInputStream(new FileInputStream("src/README.md"));
//            OutputStream out  = new BufferedOutputStream();
            /**
             * 缓冲区
             */
            byte [] buf  = new byte[1024];
            int bytesRead = in.read(buf);
            while(bytesRead != -1)
            {
                for(int i=0;i<bytesRead;i++)
                    System.out.print((char)buf[i]);
//                out.write(bytesRead);
                bytesRead = in.read(buf);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




    public static void FileNIOTest(){
        RandomAccessFile accessFile = null;
        try {
            accessFile = new RandomAccessFile("src/README.md","rw");
            FileChannel fileChannel = accessFile.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(1024);
            try {
                int bytesRead = fileChannel.read(buf);
                System.out.println(bytesRead);
                while(bytesRead!=-1){
                    /**
                     *  将Buffer从写模式切换到读模式
                     */
                    buf.flip();
                    /**
                     * 判断还有没有元素
                     */
                    while(buf.hasRemaining()){
                        System.out.print((char)buf.get());
                    }
                    /**
                     * 删除写入数据
                     */
                    buf.compact();
                    bytesRead = fileChannel.read(buf);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                if(accessFile!=null){
                    accessFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 模拟客户端和服务端的通信
     */
    public static void socketNIOTest(){

    }

    public static void client(){
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
                while(true){
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
            }
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

    public static void server(){
        int port = 8080;
        logger.info("Server started ...");
        logger.info("Server listening on port: " + port);
        ServerSocket serverSocket = null;
        InputStream in = null;
        try
        {
            serverSocket = new ServerSocket(port);
            int recvMsgSize = 0;
            byte[] recvBuf = new byte[1024];
            while(true){
                Socket clntSocket = serverSocket.accept();
                //返回此套连接的端点的地址 ， 如果未连接则返回 null
                SocketAddress clientAddress = clntSocket.getRemoteSocketAddress();
                System.out.println("Handling client at "+clientAddress);
                in = clntSocket.getInputStream();
                while((recvMsgSize=in.read(recvBuf))!=-1){
                    byte[] temp = new byte[recvMsgSize];
                    System.arraycopy(recvBuf, 0, temp, 0, recvMsgSize);
//                    System.out.println(new String(temp));
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally{
            try{
                if(serverSocket!=null){
                    serverSocket.close();
                }
                if(in!=null){
                    in.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void serverThread(int port){
        logger.info("Server started ..");
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(port);
            int recvMsgSize = 0;
            byte[] recvBuf = new byte[1024];
            while(true){
                //一旦有堵塞，则表示服务器和客户端获得了连接
                Socket client = serverSocket.accept();
                SocketAddress socketAddress = client.getRemoteSocketAddress();
                System.out.println("Handling client at "+socketAddress);
                //处理这次连接
                new HandlerThread(client);
            }
        } catch (IOException e) {
            System.out.println("服务器异常: " + e.getMessage());
        }
    }





}



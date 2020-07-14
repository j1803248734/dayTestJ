package http;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * 服务类
 */
public class Server implements Runnable{

    private Logger logger = Logger.getLogger(Server.class);

    private int port;

    private Selector selector;

    private ServerHandler handler;

    public Server(int port) throws IOException {
        this.port = port;
        //  生成一个监听器
        ServerSocketChannel sschanner = ServerSocketChannel.open();
        // 将监听设置为异步方式  异步
        sschanner.configureBlocking(false);
        //  生成选择器  可以处理多个channel
        selector = Selector.open();
        // 侦听器绑定到端口
        sschanner.socket().bind(new InetSocketAddress(port));
        // 注册侦听端所选的异步信号OP_ACCEPT
        sschanner.register(selector , SelectionKey.OP_ACCEPT);

    }

    @Override
    public void run() {
        logger.info(" Server started ");
        logger.info(" Server listening on port: " + port);
        try{
            while(true){
                if(selector.select() > 0){
                    Iterator it = selector.selectedKeys().iterator();
                    while(it.hasNext()){
                        SelectionKey key = (SelectionKey)it.next();
                        //监听端信号触发
                        if(key.isAcceptable()){
                            // 返回此关联键所绑定的通道
                            ServerSocketChannel server = (ServerSocketChannel) key.channel();
                            // 接收一个新的连接
                            SocketChannel sc = server.accept();
                            //非阻塞
                            sc.configureBlocking(false);
//                            设置该socket的异步信号OP_READ，
                            sc.register(selector , SelectionKey.OP_READ);
                        }
                        // 可读信号
                        if(key.isReadable()){
                            readMessage(key);
                        }
                        it.remove();
                    }
                }
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    public void readMessage(SelectionKey key){
        try{
            SocketChannel sc = (SocketChannel) key.channel();
            long start = System.currentTimeMillis();
            handler.read(sc);
            logger.info("the process of data elapsed "
                    + (System.currentTimeMillis() - start) * 1.0 / 1000
                    + " second.");
        } catch (IOException e) {
            logger.warn(e.getMessage());
        }finally {
            key.cancel();
        }
    }

    public void setHandler(ServerHandler handler) {
        this.handler = handler;
    }


}

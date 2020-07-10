package NIOTest;


import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * 客户端
 */
public class Client {

    private Logger logger =  Logger.getLogger(Client.class);

    private SocketChannel schannel = null;

    private FileChannel fchannel = null;

    private RandomAccessFile raf = null;

    private Client(String host , int port) throws IOException {
        InetSocketAddress addr = new InetSocketAddress(host,port);
        //新增schannel
        schannel = SocketChannel.open();
        //连接到server
        schannel.connect(addr);
        while(schannel.finishConnect())
            ;
        logger.info("connection has been established!...");

    }
}

package http.httpNio;

import com.sun.corba.se.spi.activation.Server;
import org.apache.log4j.Logger;
import org.omg.CORBA.*;
import org.omg.CORBA.Object;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NioServer implements Server , Runnable {

    private Logger logger = Logger.getLogger(NioServer.class);

    private Thread serverthread;

    private Integer port = 8080;

    private  boolean running = false;
    //volatile  始终是最新值
    private static volatile NioServer server;

    private CloudService cloudService;

    private ServerSocketChannel serverSocketChannel;

    private ByteBuffer readBuffer = ByteBuffer.allocate(8192);

    public static final ExecutorService requestWork = Executors.newCachedThreadPool();

    public static NioServer getInstance(){
        if(server == null){
            //同步锁
            synchronized (NioServer.class){
                if (server == null){
                    server = new NioServer();
                }
            }
        }
        return server;
    }

    public synchronized  void start(){
        if(running){
            logger.info("服务器已经启动");
            return;
        }
        serverthread = new Thread(this);
        serverthread.start();
        this.running = true;
    }

    public void stop(){
        try {
            serverSocketChannel.close();
            serverthread.stop();
        } catch (IOException e) {
            logger.error("error_NioServer_stop", e);
        }
    }

    @Override
    public void shutdown() {

    }

    @Override
    public void install() {

    }

    @Override
    public void uninstall() {

    }

    @Override
    public  void run() {
        try {
            //打开监听通道
            serverSocketChannel = ServerSocketChannel.open();
            // 检索与此通道关联的服务器套接字。将通道的套接字绑定到本地地址，并配置套接字以监听连接
            serverSocketChannel.socket().bind(new InetSocketAddress(port));
            // SelectableChannel对象的多路复用器  选择器保持打开，直到通过其close方法关闭。  选择器的可选频道注册由SelectionKey对象表示。 选择器保存三组选择键：
            Selector selector = Selector.open();
            // 异步
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector , SelectionKey.OP_ACCEPT);
            while(true){
                selector.select();
                Iterator<SelectionKey> selectedKeys  = selector.selectedKeys().iterator();
                SelectionKey key = null;
                while(selectedKeys.hasNext()){
                    key = selectedKeys.next();
                    selectedKeys.remove();
                }
//                判断此密钥是否有效
                if(!key.isValid()){
                    continue;
                }
                //: 测试此密钥的通道是否已准备好接受新的套接字连接。
                if(key.isAcceptable()){
                    accept(key);
                }
                if(key.isReadable()){
                    read(key);
                }
                if(key.isWritable()){
                    write(key);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected void accept(SelectionKey key){
        try{
//            译: 返回为其创建密钥的通道。
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
//            返回为其创建此键的选择器。
            socketChannel.register(key.selector() , SelectionKey.OP_READ);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Request  read(SelectionKey key)  {
        Request request = null;
        SocketChannel socketChannel = (SocketChannel) key.channel();
        try{
            //写到readbuffer中
            int readnum = socketChannel.read(readBuffer);
            if (readnum == -1) {
                socketChannel.close();
                key.cancel();
                return null;
            }
            //转化为写模式
            readBuffer.flip();
            byte [] buffers = new byte[readBuffer.limit()];
            readBuffer.get(buffers);
            request = HttpParser.decodeReq(buffers);
//            requestWork.execute(new RequestWorker(request, key, cloudService));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return request;
    }

    protected void write(SelectionKey key) throws IOException {
        //            检索当前附件。
        ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
        if(byteBuffer == null || byteBuffer.hasRemaining()){
            return;
        }
        SocketChannel socketChannel = (SocketChannel) key.channel();
        socketChannel.write(byteBuffer);
        if(!byteBuffer.hasRemaining()){
            //此键的兴趣集设置为给定值。
            key.interestOps(SelectionKey.OP_READ);

            byteBuffer.clear();

        }
        socketChannel.close();
    }


    @Override
    public boolean _is_a(String repositoryIdentifier) {
        return false;
    }

    @Override
    public boolean _is_equivalent(Object other) {
        return false;
    }

    @Override
    public boolean _non_existent() {
        return false;
    }

    @Override
    public int _hash(int maximum) {
        return 0;
    }

    @Override
    public Object _duplicate() {
        return null;
    }

    @Override
    public void _release() {

    }

    @Override
    public Object _get_interface_def() {
        return null;
    }

    @Override
    public org.omg.CORBA.Request _request(String operation) {
        return null;
    }

    @Override
    public org.omg.CORBA.Request _create_request(Context ctx, String operation, NVList arg_list, NamedValue result) {
        return null;
    }

    @Override
    public org.omg.CORBA.Request _create_request(Context ctx, String operation, NVList arg_list, NamedValue result, ExceptionList exclist, ContextList ctxlist) {
        return null;
    }

    @Override
    public Policy _get_policy(int policy_type) {
        return null;
    }

    @Override
    public DomainManager[] _get_domain_managers() {
        return new DomainManager[0];
    }

    @Override
    public Object _set_policy_override(Policy[] policies, SetOverrideType set_add) {
        return null;
    }
}

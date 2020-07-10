package test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import org.apache.log4j.Logger;

/**
 * 服务器
 * @version 1.0.0
 * 下午01:34:04
 */
public class Server implements Runnable {

	private Logger logger = Logger.getLogger(Server.class);

	private int port;

	private Selector selector;

	private ServerHandler handler;

	public Server(int port) throws IOException {
		this.port = port;

		// 生成一个侦听端
		ServerSocketChannel sschannel = ServerSocketChannel.open();
		// 将侦听端设为异步方式
		sschannel.configureBlocking(false);
		// 生成一个信号监视器
		selector = Selector.open();
		// 侦听端绑定到一个端口
		sschannel.socket().bind(new InetSocketAddress(port));
		// 设置侦听端所选的异步信号OP_ACCEPT
		sschannel.register(selector, SelectionKey.OP_ACCEPT);
	}

	public void run() {
		logger.info("Server started ...");
		logger.info("Server listening on port: " + port);
		try {

			while (true) {
				// 如果有IO事件发生
				if (selector.select() > 0) {
					Iterator it = selector.selectedKeys().iterator();
					while (it.hasNext()) {
						SelectionKey key = (SelectionKey) it.next();
						if (key.isAcceptable()) {// 侦听端信号触发
							ServerSocketChannel server = (ServerSocketChannel) key
									.channel();
							// 接受一个新的连接
							SocketChannel sc = server.accept();
							// 设置为非阻塞
							sc.configureBlocking(false);

							// 设置该socket的异步信号OP_READ:当socket可读时，
							sc.register(selector, SelectionKey.OP_READ);
						}
						if (key.isReadable()) {// 某socket可读信号

							// 读取接收到的数据
							readMessage(key);
						}
						// 删除已处理完的key
						it.remove();
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * 读取并解析数据
	 * 
	 * @since
	 * @param key
	 */
	public void readMessage(SelectionKey key) {
		try {
			// 由key获取指定socketchannel的引用
			SocketChannel sc = (SocketChannel) key.channel();
			long start = System.currentTimeMillis();
			handler.read(sc);
			logger.info("the process of data elapsed "
					+ (System.currentTimeMillis() - start) * 1.0 / 1000
					+ " second.");
		} catch (IOException e) {
			logger.warn(e.getMessage());
		} finally {
			key.cancel();
		}
	}

	public void setHandler(ServerHandler handler) {
		this.handler = handler;
	}

	public static void main(String args[]) throws IOException {
		Server server = new Server(8848);
		server.setHandler(new ServerHandler());
		new Thread(server).start();
	}
}

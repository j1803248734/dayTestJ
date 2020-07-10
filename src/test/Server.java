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
 * ������
 * @version 1.0.0
 * ����01:34:04
 */
public class Server implements Runnable {

	private Logger logger = Logger.getLogger(Server.class);

	private int port;

	private Selector selector;

	private ServerHandler handler;

	public Server(int port) throws IOException {
		this.port = port;

		// ����һ��������
		ServerSocketChannel sschannel = ServerSocketChannel.open();
		// ����������Ϊ�첽��ʽ
		sschannel.configureBlocking(false);
		// ����һ���źż�����
		selector = Selector.open();
		// �����˰󶨵�һ���˿�
		sschannel.socket().bind(new InetSocketAddress(port));
		// ������������ѡ���첽�ź�OP_ACCEPT
		sschannel.register(selector, SelectionKey.OP_ACCEPT);
	}

	public void run() {
		logger.info("Server started ...");
		logger.info("Server listening on port: " + port);
		try {

			while (true) {
				// �����IO�¼�����
				if (selector.select() > 0) {
					Iterator it = selector.selectedKeys().iterator();
					while (it.hasNext()) {
						SelectionKey key = (SelectionKey) it.next();
						if (key.isAcceptable()) {// �������źŴ���
							ServerSocketChannel server = (ServerSocketChannel) key
									.channel();
							// ����һ���µ�����
							SocketChannel sc = server.accept();
							// ����Ϊ������
							sc.configureBlocking(false);

							// ���ø�socket���첽�ź�OP_READ:��socket�ɶ�ʱ��
							sc.register(selector, SelectionKey.OP_READ);
						}
						if (key.isReadable()) {// ĳsocket�ɶ��ź�

							// ��ȡ���յ�������
							readMessage(key);
						}
						// ɾ���Ѵ������key
						it.remove();
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * ��ȡ����������
	 * 
	 * @since
	 * @param key
	 */
	public void readMessage(SelectionKey key) {
		try {
			// ��key��ȡָ��socketchannel������
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

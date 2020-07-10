package test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

import org.apache.log4j.Logger;

/**
 * �ͻ���
 * 
 * @version 1.0.0
 */
public class Client {

	private Logger logger = Logger.getLogger(Client.class);

	private SocketChannel schannel;

	private FileChannel fchannel;

	private RandomAccessFile raf;

	public Client(String host, int port) throws IOException {
		InetSocketAddress addr = new InetSocketAddress(host, port);
		// ����һ��socketchannel
		schannel = SocketChannel.open();

		// ���ӵ�server
		schannel.connect(addr);
		while (!schannel.finishConnect())
			;
		logger.info("connection has been established!...");
	}

	/**
	 * ���͵����ļ�
	 * 
	 * @param fileType
	 *            �ļ�����
	 * @param file
	 *            �ļ�
	 * @throws IOException
	 */
	public void upload(int fileType, File file) throws IOException {
		logger.debug("uploading " + file.getName());
		try {
			MsgHeader header = new MsgHeader();
			header.setFileType(fileType);
			String filename = file.getName();
			if (filename.getBytes().length > MsgHeader.MAX_FILENAME_SIZE) {
				logger.error("The file's name must be less than "
						+ MsgHeader.MAX_FILENAME_SIZE);
				return;
			}
			header.setFileName(file.getName());
			raf = new RandomAccessFile(file, "r");
			int length = (int) raf.length();
			header.setFileLength(length);
			fchannel = raf.getChannel();
			send(header);
			send(fchannel);

		} catch (IOException e) {
			logger.error(e.getMessage());
		} finally {
			try {
				if (fchannel != null) {
					fchannel.close();
				}
				if (raf != null) {
					raf.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
	}

	/**
	 * 
	 * @param type
	 *            �ļ�����
	 * @param filePath
	 *            �ļ�·��,�����ļ����ļ���·��
	 * @throws IOException
	 */
	public void upload(int type, String filePath) throws IOException {
		File file = new File(filePath);
		if (!file.exists()) {
			logger.error(file.getName() + " file not found!");
			return;
		}
		if (file.isFile()) {
			upload(type, file);
		}
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					upload(type, files[i]);
				}
			}
		}
	}

	/**
	 * ������Ϣͷ
	 * 
	 * @param header
	 * @throws IOException
	 */
	public void send(MsgHeader header) throws IOException {
		ByteBuffer buffer = ByteBuffer.wrap(header.toByteArray());
		while (buffer.hasRemaining()) {
			schannel.write(buffer);
		}
	}

	/**
	 * �����ļ�
	 * 
	 * @since
	 * @param channel
	 * @throws IOException
	 */
	public void send(FileChannel channel) throws IOException {
		MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0,
				channel.size());
		while (buffer.hasRemaining()) {
			schannel.write(buffer);
		}
	}

	/**
	 * �ر�����
	 * 
	 * @since
	 */
	public void close() {
		try {
			schannel.close();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	public static void main(String args[]) {

		long start = System.currentTimeMillis();
		try {
			Client client = new Client("localhost", 8848);
			client.upload(0, "E:/work/cuowu.txt");
//			client.upload(1, "E:/ceshi");
			client.close();
			System.out.println("the process of data elapsed "
					+ (System.currentTimeMillis() - start) * 1.0 / 1000
					+ " second.");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

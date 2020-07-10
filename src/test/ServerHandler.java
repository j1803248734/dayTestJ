package test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

import org.apache.log4j.Logger;

/**
 * 
 * @author
 * @version 1.0.0
 * @2008-10-14 下午04:05:27
 */
public class ServerHandler {

	private Logger logger = Logger.getLogger(ServerHandler.class);

	private String filePath;

	private RandomAccessFile raf;

	private FileChannel fchannel;

	private static int BUFFER_SIZE = 1024;

	private ByteBuffer r_buff = ByteBuffer.allocate(BUFFER_SIZE);

	public void read(SocketChannel sc) throws IOException {

		int off = 0;
		int count = 0;
		boolean isReadHeader = true;
		byte[] data = new byte[BUFFER_SIZE * 10];

		while (true) {
			r_buff.clear();
			int r = sc.read(r_buff);
			if (r == -1)
				break;
			if ((off + r) > data.length) {
				data = grow(data, BUFFER_SIZE * 10);
			}
			byte[] buf = r_buff.array();
			System.arraycopy(buf, 0, data, off, r);
			off += r;

			if (isReadHeader && off >= MsgHeader.HEADER_SIZE) {

				byte[] b = new byte[4];
				System.arraycopy(data, MsgHeader.HEADER_SIZE - 4, b, 0,
						b.length);
				count = bytesToInt(b);
				isReadHeader = false;
			}
			int t = count + MsgHeader.HEADER_SIZE;
			if (off >= t) {
				byte[] buff = new byte[t];
				System.arraycopy(data, 0, buff, 0, buff.length);
				this.write(buff);
				data = reduce(data, t);
				isReadHeader = true;
				off -= t;
			}
		}
	}

	/**
	 * 处理由客户端发过来的数据
	 * 
	 * @param data
	 * 
	 */
	public void write(byte[] data) {
		try {
			byte[] h_buff = new byte[MsgHeader.HEADER_SIZE];
			System.arraycopy(data, 0, h_buff, 0, h_buff.length);
			MsgHeader header = new MsgHeader(h_buff);
			logger.debug("processing of " + header.toString());

			byte[] f_buff = new byte[header.getFileLength()];
			System.arraycopy(data, MsgHeader.HEADER_SIZE, f_buff, 0,
					f_buff.length);

			switch (header.getFileType()) {
			case 0:
				filePath = "e:/serverSpace";
				break;
			case 1:
				filePath = "e:/serverSpace";
				break;
			}

			File dir = new File(filePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			filePath = filePath + File.separator + header.getFileName();

			File file = new File(filePath);
			if (file.exists()) {
				file.delete();
			}

			raf = new RandomAccessFile(file, "rw");
			fchannel = raf.getChannel();
			fchannel.write(ByteBuffer.wrap(f_buff));

		} catch (Exception e) {
			e.printStackTrace();
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
	 * 减去已经处理过的数据
	 * 
	 * @param src
	 * @param off
	 * @return
	 */
	public byte[] reduce(byte[] src, int off) {
		byte[] tmp = new byte[src.length - off];
		System.arraycopy(src, off, tmp, 0, tmp.length);
		return tmp;
	}

	/**
	 * 数组扩容
	 * 
	 * @param src
	 *            byte[] 源数组数据
	 * @param size
	 *            int 扩容的增加量
	 * @return byte[] 扩容后的数组
	 */
	public byte[] grow(byte[] src, int size) {
		byte[] tmp = new byte[src.length + size];
		System.arraycopy(src, 0, tmp, 0, src.length);
		return tmp;
	}

	public int bytesToInt(byte[] b) {
		int s = 0;
		for (int i = 0; i < 3; i++) {
			if (b[i] >= 0) {
				s = s + b[i];
			} else {
				s = s + 256 + b[i];
			}
			s = s * 256;
		}
		if (b[3] >= 0) {
			s = s + b[3];
		} else {
			s = s + 256 + b[3];
		}
		return s;
	}

}

package test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

public class MsgHeader {

	private Logger logger = Logger.getLogger(MsgHeader.class);

	/** ��Ϣͷ�ĳ��� */
	public final static int HEADER_SIZE = 28;

	/** �ļ�����󳤶� */
	public final static int MAX_FILENAME_SIZE = 20;

	/**
	 * �ļ�����
	 */
	private int fileType;

	/**
	 * �ļ�����
	 */
	private byte[] fileName = new byte[MAX_FILENAME_SIZE];

	/**
	 * �ļ�����
	 */
	private int fileLength;

	public MsgHeader() {

	}

	public MsgHeader(byte[] buff) throws IOException {
		ByteArrayInputStream bais = new ByteArrayInputStream(buff);
		DataInputStream reader = new DataInputStream(bais);
		this.fileType = reader.readInt();
		reader.read(this.fileName);
		this.fileLength = reader.readInt();
	}

	public int getFileType() {
		return fileType;
	}

	public void setFileType(int fileType) {
		this.fileType = fileType;
	}

	public String getFileName() {
		return new String(fileName).trim();
	}

	public void setFileName(String fileName) {
		byte[] b = fileName.getBytes();
		System.arraycopy(b, 0, this.fileName, 0, b.length);
	}

	public int getFileLength() {
		return fileLength;
	}

	public void setFileLength(int fileLength) {
		this.fileLength = fileLength;
	}

	public byte[] toByteArray() {
		byte[] buff = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		try {
			dos.writeInt(this.fileType);
			dos.write(this.fileName);
			dos.writeInt(this.fileLength);
			buff = baos.toByteArray();
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			try {
				dos.close();
				baos.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}

		return buff;
	}

	public String toString() {
		return "fileType=" + this.getFileType() + " fileName="
				+ this.getFileName() + " fileLength=" + this.fileLength;
	}
}

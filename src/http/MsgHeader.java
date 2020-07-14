package http;


import org.apache.log4j.Logger;

import java.io.*;

/**
 *      消息处理
 */
public class MsgHeader {

    private Logger logger = Logger.getLogger(MsgHeader.class);

    /** 消息头长度 */
    public final static int HEADER_SIZE = 28;

    /**  文件名的最大长度    */
    public final static int MAX_FILENAME_SIZE = 20;

    /**
     * 文件类型
     */
    private int fileType;
    /**
     * 文件名称
     */
    private byte[] fileName = new byte[MAX_FILENAME_SIZE];
    /**
     * 文件长度
     */
    private int fileLength;

    public MsgHeader() {

    }

    public MsgHeader(byte[] buff) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(buff);
        DataInputStream reader = new DataInputStream(bais);
        // 返回长度
        this.fileType  = reader.readInt();
        reader.read(this.fileName);
        this.fileLength = reader.readInt();

    }

    public int getFileType(){
        return fileType;
    }

    public void setFileType(int fileType){
        this.fileType = fileType;
    }

    public String getFileName() {
        return new String(fileName).trim();
    }

    public void setFileName(String fileName) {
        byte[] b = fileName.getBytes();
        System.arraycopy(b , 0 , this.fileName , 0 , b.length);
    }

    public int getFileLength() {
        return fileLength;
    }

    public void setFileLength(int fileLength) {
        this.fileLength = fileLength;
    }

    public byte[] toByteArray(){
        byte[] buff = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        try{
            dos.writeInt(this.fileType);
            dos.write(this.fileName);
            dos.writeInt(this.fileLength);
            //创建字节数组
            buff = baos.toByteArray();
        }catch(Exception e){
            logger.error(e.getMessage());
        }finally {
            try {
                dos.close();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buff;
    }
}

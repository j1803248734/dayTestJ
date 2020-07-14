package http;


import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * 文件上传处理类
 */
public class ServerHandler {
    private Logger logger = Logger.getLogger(ServerHandler.class);

    private String filepath;

    private RandomAccessFile raf;

    private FileChannel fchannel;

    private static int BUFFER_SIZE = 1024;
    /**
     * 字节缓冲区
     */
    private ByteBuffer r_buff = ByteBuffer.allocate(BUFFER_SIZE);

    public void read(SocketChannel sc) throws IOException {
        // 哨兵
        int off = 0;
        int count = 0;
        boolean isReadHeader = true;
        byte[] data = new byte[BUFFER_SIZE * 10];
        while(true){
            r_buff.clear();
//            从该通道读取到给定缓冲区的字节序列
//            channel read the byte sequence of that support of buffer
            int r = sc.read(r_buff);
            if(r == -1){
                break;
            }
            //起始位置 + 长度 > 总长度   扩容
//            start sentry + buffer array length  > data array length
            if((off + r) > data.length){
                //数组扩容
//                array expansion
                data = grow(data , BUFFER_SIZE * 10);
            }
//            返回支持此缓冲区的字节数组
//            return array  of  byte that support this buffer
            byte[] buf = r_buff.array();
//            将缓冲区的字节数组添加到data数组里面 off是要开始的下标 r是字节数组的长度
//            Add the byte array of buffer to the data array . off is start sentry .r is the length of byte array
            System.arraycopy(buf , 0 , data ,off , r);
            off += r;
//            是否已读   消息头长度是否大于规定的长度
            if(isReadHeader && off > MsgHeader.HEADER_SIZE){
                byte[] b = new byte[4];
//                取出最后四个字节
                System.arraycopy(data, MsgHeader.HEADER_SIZE - 4, b, 0,
                        b.length);
//                转化为int
                count = bytesToInt(b);
                isReadHeader = false;
            }
            int t = count + http.MsgHeader.HEADER_SIZE;
            if(off >= t){
                byte[] buff = new byte[t];
                System.arraycopy(data,0,buff,0,buff.length);
                this.write(buff);
                data = reduce(data , t);
                isReadHeader = true;
                off -= t;
            }
        }
    }

    protected  void write(byte[] data){
        try{
            byte[] h_buff = new byte[MsgHeader.HEADER_SIZE];
            System.arraycopy(data , 0 , h_buff , 0 , h_buff.length);
            MsgHeader header = new MsgHeader(h_buff);
            logger.debug("processing of "+ header.toString());
            byte[] f_buff = new byte[header.getFileLength()];
            System.arraycopy(data , MsgHeader.HEADER_SIZE , f_buff , 0 , f_buff.length);
            switch (header.getFileType()){
                case 0:
                    filepath = "e:/serverSpace";
                    break;
                case 1:
                    filepath = "e:/serverSpace";
                    break;
            }
            File dir  = new File(filepath);
            if(!dir.exists()){
                dir.mkdirs();
            }
            filepath = filepath+File.separator+header.getFileName();
            File file = new File(filepath);
            if(file.exists()){
                file.delete();
            }
            raf = new RandomAccessFile(file , "rw");
            fchannel = raf.getChannel();
            fchannel.write(ByteBuffer.wrap(f_buff));
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }finally {
           try{
               if(fchannel!=null){
                   fchannel.close();
               }
               if(raf != null){
                   raf.close();
               }
           } catch (IOException e) {
               logger.error(e.getMessage());
           }
        }
    }

    /**
     * 减去已经处理过的数据
     * @param src
     * @param off
     * @return
     */
    public byte[] reduce(byte[] src , int off){
        byte[] tmp = new byte[src.length - off];
        //截取字符串
        System.arraycopy(src , off , tmp , 0 , tmp.length);
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
    protected byte[] grow(byte[] src, int size){
        byte[] tmp = new byte[src.length + size];
        System.arraycopy(src , 0 , tmp , 0 , src.length);
        return tmp;
    }


    public int bytesToInt(byte[] b){
        return b[3] & 0xFF |
                (b[2] & 0xFF) << 8 |
                (b[1] & 0xFF) << 16 |
                (b[0] & 0xFF) << 24;
    }



}

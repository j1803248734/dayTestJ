package http;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class Client {
    private Logger logger = Logger.getLogger(Client.class);

    private SocketChannel schannel;

    private FileChannel fchannel;

    private RandomAccessFile raf;

    public Client(String host , int port) throws IOException {
        InetSocketAddress addr = new InetSocketAddress(host , port);
        //生成一个socketchannel
        schannel = SocketChannel.open();
        schannel.connect(addr);
        while(!schannel.finishConnect())
            ;
        logger.info("connection has been established!...");
    }


    public void upload(int fileType , File file){
        logger.debug("uploading "+file.getName());
        try{
            MsgHeader header = new MsgHeader();
            header.setFileType(fileType);
            String filename = file.getName();
            if(filename.getBytes().length > MsgHeader.MAX_FILENAME_SIZE){
                logger.error("The file's name must be less than"+MsgHeader.MAX_FILENAME_SIZE);
                return ;
            }
            header.setFileName(filename);
            //创建一个随机访问文件流 并且只读
            raf = new RandomAccessFile(file , "r");
            int length = (int) raf.length();
            header.setFileLength(length);
            //获取此文件的channel
            fchannel = raf.getChannel();
            send(header);
            send(fchannel);
        }catch (IOException e) {
//            e.printStackTrace();
            logger.error(e.getMessage());
        }finally {
            try{
                if(fchannel!=null){
                    fchannel.close();
                }
                if(raf!=null){
                    raf.close();
                }
            } catch (IOException e) {
               logger.error(e.getMessage());
            }
        }
    }

    public void upload(int type, String filePath){
        File file = new File(filePath);
        if(!file.exists()){
            logger.error(file.getName() + " file not found");
            return;
        }
        // 是否为普通文件
        if(file.isFile()){
            upload(type, file);
        }
        // 是否为路径
        if(file.isDirectory()){
            File[] files = file.listFiles();
            if(files != null && files.length > 0){
                for(int i = 0 ; i < files.length ; i++ ){
                    upload(type , files[i]);
                }
            }
        }
    }


    /**
     * 发送消息头
     */
    public void send(MsgHeader header) throws IOException {
        ByteBuffer buffer = ByteBuffer.wrap(header.toByteArray());
        while(buffer.hasRemaining()){
            schannel.write(buffer);
        }
    }


    public void send(FileChannel channel) throws IOException {
        /**
         * 直接将此部分文件的区域映射到内存中
         */
        MappedByteBuffer buff = channel.map(FileChannel.MapMode.READ_ONLY , 0 , channel.size());
        while(buff.hasRemaining()){
            schannel.write(buff);
        }    }

    public void close(){
        try {
            schannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

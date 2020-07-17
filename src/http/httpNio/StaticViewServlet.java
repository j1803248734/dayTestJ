package http.httpNio;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StaticViewServlet implements CloudServlet{

    private final static Logger logger = Logger.getLogger(StaticViewServlet.class);

    private String staticRootPath;

    public static Pattern p  = Pattern.compile("^/static/\\S+");


    @Override
    public boolean match(Request request) {
        String path = request.getPath();
        Matcher matcher = p.matcher(path);
        return matcher.matches();
    }

    @Override
    public void init(Request request, Response response) {
        staticRootPath = "static.resource.path";
    }

    @Override
    public void doService(Request request, Response response) {
        String path = request.getPath();
        String fileRelativePath = path.substring(8);
        String absolutePath = staticRootPath+ "/"+fileRelativePath;
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(absolutePath , "r");
            FileChannel fchannel = raf.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) fchannel.size());
            fchannel.read(byteBuffer);
//            write - > read
            byteBuffer.flip();
            byte [] bytes = new byte[byteBuffer.limit()];
            byteBuffer.get(bytes);
            //写入输出流
            response.getOutputStream().write(bytes);
        } catch (IOException e) {
            logger.error("error_StaticViewServlet_doService 异常", e);
        }
    }
}

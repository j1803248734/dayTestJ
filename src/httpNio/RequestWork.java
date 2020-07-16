package httpNio;

import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class RequestWork implements Runnable {
    private final static Logger logger = Logger.getLogger(RequestWork.class);

    private Request request;

    private SelectionKey key;

    private SocketChannel channel;

    private CloudService cloudService;

    public RequestWork(Request request, SelectionKey key, CloudService cloudService) {

        this.request = request;

        this.key = key;

        this.channel = (SocketChannel) key.channel();

        this.cloudService = cloudService;

    }

    @Override
    public void run() {
        Response response = new Response();
        try {

            cloudService.doService(request, response);

        } catch (Exception e) {

            response.setCode(404);

            response.setMsg("404");

            ByteArrayOutputStream outputStream = response.getOutputStream();

            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("404.html");

            byte bytes [] = new byte [1024];

            int len;

            try {

                while ((len = inputStream.read(bytes)) > -1) {

                    outputStream.write(bytes, 0, len);

                }

            } catch (IOException e1) {

                logger.error("error_requestWork_write404", e);

            }
        }
        byte[] resheader = HttpParser.encodeResHeader(response);
        byte[] body = response.getOutputStream().toByteArray();

        ByteBuffer byteBuffer = ByteBuffer.allocate(resheader.length + body.length);
        byteBuffer.put(resheader);
        byteBuffer.put(body);
        byteBuffer.flip();
//        将输出流绑定至附件
        key.attach(byteBuffer);
        // 注册写事件

        key.interestOps(SelectionKey.OP_WRITE);

        key.selector().wakeup();

    }
}

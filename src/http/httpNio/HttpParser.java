package http.httpNio;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HttpParser {
    private final static Logger logger = Logger.getLogger(HttpParser.class);
    /**

     * <p>解析http请求体</p>

     *

     * @param buffers

     * @return

     */

    public static Request decodeReq(byte[] buffers){
        Request request = new Request();
        if(buffers != null){
            String resString  = new String(buffers);
            logger.info(resString);
            String[] headers = resString.trim().split("\r\n");
            if(headers.length > 0){
                String firstLine = headers[0];
                //按照空格解析字符串
                //mothod url  协议版本
                String mainInfo [] = firstLine.split("\\s+");
                request.setMethod(mainInfo[0]);
                try{
                    request.setUrl(URLDecoder.decode(mainInfo[1],"UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                request.setHttpVersion(mainInfo[2]);



                //解析header
                Map<String , String> headersMap  = new HashMap<>();
                for(int i = 1 ; i < headers.length ; ++i){
                    String entryStr  = headers[i];
                    String[] entry = entryStr.trim().split(":");
                    headersMap.put(entry[0].trim() , entry[1].trim());
                }
                request.setHeaders(headersMap);


                //解析参数
                String url = request.getUrl();
                Map<String , String> attribute = new HashMap<>();
                request.setPath(url);
                if(url != null){
                    int indexOfParam = url.indexOf("?");
                    if(indexOfParam > 0){
                        request.setPath(url.substring( 0 ,indexOfParam));
                        String queryStr = url.substring(indexOfParam + 1 , url.length());
                        String [] params = queryStr.split("&");
                        for(String param : params){
                            String [] entry = param.split("=");
                            if(entry.length > 0){
                                String key = entry[0];
                                String value  = entry.length > 1 ? entry[1] : "";
                                attribute.put(key , value);
                            }
                        }
                    }
                }
                request.setAttribute(attribute);
            }
        }
        return request;
    }

    /**
     * 返回http响应字节流
     * @param response
     * @return
     */
    public static byte[] encodeResHeader(Response response){
        StringBuilder sb = new StringBuilder();
        sb.append(response.getProtocol()+" "+response.getCode()+" "+response.getMsg());
        sb.append("\r\n");
        Map<String , String> headers = response.getHeaders();
        headers.forEach((key, value) -> {
            sb.append(key);
            sb.append(":");
            sb.append(value);
            sb.append("\r\n");
        });
        sb.append("\r\n");
        String resString = sb.toString();
        byte [] bytes = null;
        bytes = resString.getBytes(StandardCharsets.UTF_8);

        return bytes;
    }

}

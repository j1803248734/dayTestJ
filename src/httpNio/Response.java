package httpNio;

import java.io.ByteArrayOutputStream;
import java.util.*;

public class Response {

    private Integer code;

    private String protocol = "HTTP/1.1";

    private String msg;

    private Map<String , String> headers;

    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    public Response(){
        this.code = 200;
        this.msg = "yes";
        headers = new HashMap<>();
        headers.put("Content-Type", "text/html;charset=UTF-8");
        headers.put("Server", "cloud http v1.0");
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public ByteArrayOutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(ByteArrayOutputStream outputStream) {
        this.outputStream = outputStream;
    }
}

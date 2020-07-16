package httpNio;

public class CloudHttpServer {
    public static void main(String[] args) {
        startServer();
    }
    public static void startServer() {

        NioServer.getInstance().start();

    }
}

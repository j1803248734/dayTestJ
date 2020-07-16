package httpNio;

import com.sun.corba.se.spi.activation.Server;
import org.apache.log4j.Logger;
import org.omg.CORBA.*;
import org.omg.CORBA.Object;
import org.omg.CORBA.Request;

import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NioServer implements Server , Runnable {

    private Logger logger = Logger.getLogger(NioServer.class);

    private Thread thread;

    private Integer port;

    private  boolean running = false;
    //volatile  始终是最新值
    private static volatile NioServer server;

    private CloudService cloudService;

    private ServerSocketChannel serverSocketChannel;

    private ByteBuffer readBuffer = ByteBuffer.allocate(8192);

    public static final ExecutorService requestWork = Executors.newCachedThreadPool();

    public static NioServer getInstance(){
        if(server == null){
            synchronized (NioServer.class){
                if (server == null){
                    server = new NioServer();
                }
            }
        }
        return server;
    }

    @Override
    public void shutdown() {

    }

    @Override
    public void install() {

    }

    @Override
    public void uninstall() {

    }

    @Override
    public void run() {

    }

    @Override
    public boolean _is_a(String repositoryIdentifier) {
        return false;
    }

    @Override
    public boolean _is_equivalent(Object other) {
        return false;
    }

    @Override
    public boolean _non_existent() {
        return false;
    }

    @Override
    public int _hash(int maximum) {
        return 0;
    }

    @Override
    public Object _duplicate() {
        return null;
    }

    @Override
    public void _release() {

    }

    @Override
    public Object _get_interface_def() {
        return null;
    }

    @Override
    public Request _request(String operation) {
        return null;
    }

    @Override
    public Request _create_request(Context ctx, String operation, NVList arg_list, NamedValue result) {
        return null;
    }

    @Override
    public Request _create_request(Context ctx, String operation, NVList arg_list, NamedValue result, ExceptionList exclist, ContextList ctxlist) {
        return null;
    }

    @Override
    public Policy _get_policy(int policy_type) {
        return null;
    }

    @Override
    public DomainManager[] _get_domain_managers() {
        return new DomainManager[0];
    }

    @Override
    public Object _set_policy_override(Policy[] policies, SetOverrideType set_add) {
        return null;
    }
}

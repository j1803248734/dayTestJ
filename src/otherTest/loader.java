package otherTest;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

public class loader {
    static{
        System.out.println("jiangLoader!!!");
    }
    public static  int i = 1;
    public static final String one = "jiang";
}


class loader1 extends loader{
    static{
        System.out.println("loader11111");
    }
}


class main{
    public static void main(String[] args) {
        //引用父类值 只会触发父类初始化
//        System.out.println(loader1.i);

        //无输出  不会触发初始化
//        loader1 [] l1s = new loader1[4];

        //final修饰  编译阶段会放入常量池，本质上没有调用类 不处罚初始化 去掉final就会触发初始化
//        System.out.println(loader.one);

        //测试 BootStrap ClassLoader 加载的哪些文件：
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println("---------------------------------");
        //默认加载JAVA_HOME/jre/lib/ext/目下的所有jar。  EtxClassLoader
        System.out.println(System.getProperty("java.ext.dirs"));
    }
}

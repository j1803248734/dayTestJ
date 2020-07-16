package httpNio;

import java.util.ArrayList;
import java.util.List;

public class CloudService {

    private List<CloudServlet> cloudServlets = new ArrayList<>();

    public CloudService(){
        cloudServlets.add(new StaticViewServlet());
    }

    public void doService(Request request , Response response){
        CloudServlet cloudServlet = new StaticViewServlet();
    }

    private CloudServlet doSelect(Request request){
        for(CloudServlet cloudServlet : cloudServlets){
            if(cloudServlet.match(request)){
                return cloudServlet;
            }
        }
        return new StaticViewServlet();
    }
}

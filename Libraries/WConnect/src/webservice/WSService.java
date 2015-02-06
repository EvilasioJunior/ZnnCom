
package webservice;

import java.io.*;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceFeature;


/**
 * This class is used for connection with webservices 
 * and was generate with base in JAX-WS RI
 */
public class WSService
    extends Service
{
    /*Parameters*/
    private  String targetNamespace, Name, Wsdl, Port;
    private  BufferedReader in;  
    
    
    //Initialize
    private void init(String targetName, String Port) {
        this.targetNamespace = targetName;
        this.Port = Port;
    } 
    
    //Constructors
    public WSService(URL wsdlLocation, QName serviceName, String targetName,String Port) {
        super(wsdlLocation, serviceName);
        init(targetName, Port);
    }
    
    public WSService(URL wsdlLocation, QName serviceName, String targetName,String Port, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
        init(targetName, Port);
    }

    /**
     * 
     * @return
     *     returns 
     */
    @WebEndpoint
    public WSInterface getWSPort() {
        return super.getPort(new QName(targetNamespace, Port), WSInterface.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns TesteWS
     */
    @WebEndpoint
    public WSInterface getWSPort(WebServiceFeature... features) {
        return super.getPort(new QName(targetNamespace, Port), WSInterface.class, features);
    }
    
}

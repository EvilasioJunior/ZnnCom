/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adapterZZNCOM;

import adapter.Monitoring;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import webservice.Webservice;
import webservice.WSFuncs;

/**
 *
 * @author Evilasio
 */
public class SpecificMonitor extends Monitoring{
    
    Webservice wNews;
    
    public SpecificMonitor(Webservice wMonitor, Webservice wNews){
        this.setMonitoring(wMonitor);
        this.wNews = wNews;
    }
    
    public SpecificMonitor(Webservice wNews){
        this.wNews = wNews;
    }
    
    public void seturlNews(Webservice wNews){
        this.wNews = wNews;
    }
    
    public Webservice geturlNews(){
        return this.wNews;
    }
    
    @Override
    public void monitoring(){
        
        String aux="";
        long tempoInicio=0, tempoFinal=0;
        Webservice waux = new Webservice();
        WSFuncs ws = new WSFuncs();
        ArrayList<Webservice> lstwebservice = new ArrayList<Webservice>();
        
        XStream parser = new XStream(new DomDriver("ISO-8859-1"));
        parser.setMode(XStream.NO_REFERENCES);   
        
        if(!(wNews==null)){

            tempoInicio  = System.currentTimeMillis();
            aux = ws.fService(wNews,"0");
            tempoFinal  = System.currentTimeMillis() - tempoInicio;

            waux.setDescription(wNews.getDescription());
            waux.setid(wNews.getid());
            waux.setName(wNews.getName());
            waux.setPort(wNews.getPort());
            waux.settargetName(wNews.gettargetName());
            waux.seturl(wNews.geturl());
            waux.addarguments(Long.toString(tempoFinal));
            lstwebservice.add(waux);

            String xml = parser.toXML(lstwebservice);

             //Write in the file
             try{       
                    BufferedWriter out = new BufferedWriter(new FileWriter(this.getValuesMonitor()));
                    out.write(xml);
                    out.close();
             }catch (IOException e){}
        }
        //Alert Observers
        this.Notify();
        
        seturlNews(null);
        //Time until next read
        try {  
           Thread.sleep(getTime()*1000);  //each getTime() seconds
        } catch (Exception e) {  
           e.printStackTrace();  
        }
    }
    //alterAlert();
     
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adapterZZNCOM;

import adapter.Execution;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import webservice.Webservice;

/**
 *
 * @author Evilasio
 */
public class SpecificExecution extends Execution{
  public SpecificExecution(Webservice wExecutor){
        this.setExecution(wExecutor);
    }
    
    public SpecificExecution(){
    }
    
    @Override
    public void execute(){
        //String path = "C:\\Users\\Evilasio\\Documents\\NetBeansProjects\\ZNNCOM\\";
        String path = "";
        File Services = new File(path+"Services.xml");
        Webservice waux = new Webservice();
        ArrayList<Webservice> lwebservice;
        ArrayList<Webservice> lstwebservice;
        
        //configurações para leitura xml
        XStream parser = new XStream(new DomDriver());
        parser.setMode(XStream.NO_REFERENCES);
        
        if(this.getValuesPlanning().length()>0){            
            
            if(Services.length()>0){
                lstwebservice = (ArrayList<Webservice>) parser.fromXML(Services);
            }
            else{
                lstwebservice = new ArrayList<Webservice>();
            }
            
            //List of current services and that must be removed
            lwebservice = (ArrayList<Webservice>) parser.fromXML(this.getValuesPlanning());
            
            for(Webservice w: lwebservice){
                waux.setid(w.getid());
                waux.setDescription(w.getDescription());
                waux.setName("NewServiceNewsService");
                waux.settargetName("http://com/");
                waux.seturl("http://localhost:8080/ServiceNews/NewServiceNews?wsdl");
                waux.setPort("NewServiceNewsPort");
                waux.addarguments(w.getarguments().get(0));
                waux.addarguments(w.getarguments().get(1));
                lstwebservice.add(waux);
            }

            String xml = parser.toXML(lstwebservice);

            //Write in file
            try{
                BufferedWriter outs = new BufferedWriter(new FileWriter(Services));
                outs.write(xml);  
                outs.close();
            }catch (IOException e){}

            //Clean monitor file
            try {             
                cleanfile(this.getValuesPlanning());
            } catch (IOException e) {}      
        }
    }
    
    public void cleanfile(File file) throws IOException{
        BufferedWriter outm = new BufferedWriter(new FileWriter(file));
        outm.write("");  
        outm.flush();
        outm.close();
    }  
}

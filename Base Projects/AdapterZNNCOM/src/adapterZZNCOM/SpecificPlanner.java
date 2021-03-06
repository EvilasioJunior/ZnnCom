/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adapterZZNCOM;

import adapter.Planning;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import webservice.Webservice;
import webservice.WSFuncs;

/**
 *
 * @author Evilasio
 */
public class SpecificPlanner extends Planning{
    
    public SpecificPlanner(Webservice wDesigner){
        this.setPlanning(wDesigner);
    }
    
    public SpecificPlanner(){
    }
    
    @Override
    public void plan(){
        //String path = "C:\\Users\\Evilasio\\Documents\\NetBeansProjects\\ZNNCOM\\";
        Webservice waux = new Webservice();
        WSFuncs ws = new WSFuncs();
        ArrayList<Webservice> lstwebservice = new ArrayList<Webservice>();
        
        //configurações para leitura xml
        XStream parser = new XStream(new DomDriver());
        parser.setMode(XStream.NO_REFERENCES);
        
        if(this.getValuesAnalysis().length()>0){                               
            //List of current services and that must be removed
            ArrayList<Webservice> lwebservice = (ArrayList<Webservice>) parser.fromXML(this.getValuesAnalysis());
            
            for(Webservice w: lwebservice){
                waux.setid(w.getid());
                waux.setDescription(w.getDescription());
                waux.setName(w.getName());
                waux.settargetName(w.gettargetName());
                waux.seturl(w.geturl());
                waux.setPort(w.getPort());
                waux.addarguments(ws.fService(w, w.getarguments().get(0)));
                waux.addarguments(w.getarguments().get(1));
                lstwebservice.add(waux);
            }

            String xml = parser.toXML(lstwebservice);

            //Write in file
            try{
                BufferedWriter outs = new BufferedWriter(new FileWriter(this.getValuesPlanning()));
                outs.write(xml);  
                outs.close();
            }catch (IOException e){}

            //Clean monitor file
            try {             
                cleanfile(this.getValuesAnalysis());
            } catch (IOException e) {}
        }
        this.Notify();
    }
    
    public void cleanfile(File file) throws IOException{
        BufferedWriter outm = new BufferedWriter(new FileWriter(file));
        outm.write("");  
        outm.flush();
        outm.close();
    }
}

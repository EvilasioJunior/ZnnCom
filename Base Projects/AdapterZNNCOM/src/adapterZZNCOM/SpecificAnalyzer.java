/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adapterZZNCOM;

import adapter.Analysis;
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
public class SpecificAnalyzer extends Analysis{

    int timelimit;//millisecond
    
    public SpecificAnalyzer(Webservice wAnalyzer, int timelimit){
        this.setAnalysis(wAnalyzer);
        this.timelimit = timelimit;
    }
    
    public SpecificAnalyzer(int timelimit){
        this.timelimit = timelimit;
    }

    @Override
    public void analyze(){
        String type = "0";
        Webservice waux = new Webservice();
        ArrayList<Webservice> lstwebservice = new ArrayList<Webservice>();
        
        //Configuration of XML
        XStream parser = new XStream(new DomDriver("ISO-8859-1"));
        parser.setMode(XStream.NO_REFERENCES);
        
        if(this.getValueMonitoring().length()>0){
            //List of current services and that must be removed
            ArrayList<Webservice> lwebservice = (ArrayList<Webservice>) parser.fromXML(this.getValueMonitoring());
            
            for(Webservice w: lwebservice){               
                
                if (Long.parseLong(w.getarguments().get(0))>timelimit)
                       type = "2";
                else
                       type = "1";
                
                waux.setDescription(w.getDescription());
                waux.setid(w.getid());
                waux.setName(w.getName());
                waux.setPort(w.getPort());
                waux.settargetName(w.gettargetName());
                waux.seturl(w.geturl());
                waux.addarguments(type);
                waux.addarguments(w.getarguments().get(1));
                lstwebservice.add(waux);
            }
            String xml = parser.toXML(lstwebservice);

            //Write in file
            try{
                BufferedWriter out = new BufferedWriter(new FileWriter(this.getValueAnalysis()));
                out.write(xml);  
                out.close();
            }catch (IOException e){}

            //Clean monitor file
            try {             
                cleanfile(this.getValueMonitoring());
            } catch (IOException e) {}
        }
        //this.analyzeWebservice();
        this.Notify();
    }
    
    public void cleanfile(File file) throws IOException{
        BufferedWriter outm = new BufferedWriter(new FileWriter(file));
        outm.write("");  
        outm.flush();
        outm.close();
    }
     
}

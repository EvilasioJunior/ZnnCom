/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adapterZZNCOM;

import adapter.AbstractAdapterObserver;
import adapter.Adapter;
import adapter.Analysis;
import adapter.Execution;
import adapter.Monitoring;
import adapter.Planning;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import webservice.Webservice;


/**
 *
 * @author Evilasio
 */
public class AdapterObserver implements AbstractAdapterObserver{
    
    //Attributes Observer Observed
    private Adapter adaptadorObservado;
    
    //File news services
    private File FileNews;

    public AdapterObserver(){}
       
    //Set new object observed
    public void setObserver(Adapter a){
        this.adaptadorObservado = a;
	adaptadorObservado.addAdapterObserver(this);
    }
    
    //Methods for Observer Observed    
    @Override
    public void update(Adapter a) {
        if(a == adaptadorObservado){
            adaptadorObservado.setStop(verify());
            if(updateNews()){
                adaptadorObservado.setlistMonitoring(ListtoMonitors(FiletoWebservices(getNews())));
                adaptadorObservado.setlistAnalysis(ListtoAnalyzers(FiletoWebservices(getNews())));
                adaptadorObservado.setlistPlanning(ListtoPlanners(FiletoWebservices(getNews())));
                adaptadorObservado.setlistExecution(ListtoExecutors(FiletoWebservices(getNews())));
                adaptadorObservado.setObserverListAnalysis(adaptadorObservado.getlistMonitoring());
                adaptadorObservado.setObserverListPlanning(adaptadorObservado.getlistAnalysis());
                adaptadorObservado.setObserverListExecution(adaptadorObservado.getlistPlanning());
            }
        }
    }
    
    //Set file news service
    public void setNews(File filenews){
        this.FileNews = filenews;
    }
    
    //Get file news service
    public File getNews(){
        return this.FileNews;
    }
    
    //-----Specific Functions-------//
    public Boolean verify(){      
        File sf = new File("StopFile.txt");
        String sbaux = "";
        Boolean baux = true;
        //Read the file of information about stop criteria of Adapter
        try{       
        BufferedReader in = new BufferedReader(new FileReader(sf));
        sbaux = in.readLine();
        in.close();
        }catch (IOException e){}
        
        if(sbaux!=null){
            if (!sbaux.equals(""))
                baux = false;
        }
        
        //Write in the file of information about stop criteria of Adapter
        try{       
        BufferedWriter out = new BufferedWriter(new FileWriter(sf));
        out.write(""); 
        out.close();
        }catch (IOException e){}
        
        return baux;
    } 
    
    public static List<Webservice> FiletoWebservices (File file){   
        XStream parser = new XStream(new DomDriver());
        parser.setMode(XStream.NO_REFERENCES);
        List<Webservice> lWaux = (List<Webservice>)parser.fromXML(file);
        
        return lWaux;
    }
    
    public static List<Monitoring> ListtoMonitors (List<Webservice> list){   
        List<Monitoring> laux = new ArrayList<Monitoring>();       
        for(Webservice w: list){
            Webservice waux = new Webservice(); 
            waux.setDescription(w.getDescription());
            waux.setid(w.getid());
            waux.settargetName(w.gettargetName());
            waux.setName(w.getName());
            waux.seturl(w.geturl());
            waux.setPort(w.getPort());
            waux.addarguments(w.getarguments().get(4));
            Monitoring maux = new SpecificMonitor(waux);
            maux.setValuesMonitor(w.getarguments().get(0));
            laux.add(maux);
        }
        return laux;
    }
        
    public static List<Analysis> ListtoAnalyzers (List<Webservice> list){   
        List<Analysis> laux = new ArrayList<Analysis>();
        for(Webservice w: list){
            Analysis aaux = new SpecificAnalyzer(Integer.parseInt(w.getarguments().get(3)));
            aaux.setValuesAnalysis(w.getarguments().get(1));
            laux.add(aaux);
        }
        return laux;
    }
            
    public static List<Planning> ListtoPlanners (List<Webservice> list){   
        List<Planning> laux = new ArrayList<Planning>();
        for(Webservice w: list){
            Planning paux = new SpecificPlanner();
            paux.setValuesPlanning(w.getarguments().get(2));
            laux.add(paux);
        }
        return laux;
    }
    
    public static List<Execution> ListtoExecutors (List<Webservice> list){   
        List<Execution> laux = new ArrayList<Execution>();
        for(Webservice w: list){
            Execution paux = new SpecificExecution();
            laux.add(paux);
        }
        return laux;
    }
    
    public boolean updateNews(){
        File uf = new File("UpdateFile.txt");
        String sbaux = "";
        Boolean baux = false;
        //Read the file of information about stop criteria of Adapter
        try{       
        BufferedReader in = new BufferedReader(new FileReader(uf));
        sbaux = in.readLine();
        in.close();
        }catch (IOException e){}
        
        if(sbaux!=null){
            if (!sbaux.equals("")){
                baux = true;
                //Clean Services File
                try{
                    BufferedWriter outm = new BufferedWriter(new FileWriter("Services.xml"));
                    outm.write("");  
                    outm.flush();
                    outm.close();
                }catch (IOException e){}   
            }
        }
        
        //Write in the file of information about stop criteria of Adapter
        try{       
        BufferedWriter out = new BufferedWriter(new FileWriter(uf));
        out.write("");  
        out.flush();
        out.close();
        }catch (IOException e){}
        
        return baux;
    }
    
}

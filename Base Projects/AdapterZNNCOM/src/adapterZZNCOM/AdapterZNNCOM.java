/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adapterZZNCOM;
import adapter.Adapter;
import adapter.Planning;
import adapter.Monitoring;
import adapter.Analysis;
import adapter.Execution;
import java.io.File;
import java.util.List;
import webservice.Webservice;
/**
 *
 * @author Genshiken
 */
public class AdapterZNNCOM{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) { 
            //Useful Functions
            AdapterObserver ao = new AdapterObserver();
            
            //Files contains list of web services news
            ao.setNews(new File("FileWebservices.xml"));
                      
            //Set Monitors, Analyzers and Planners use Specific Functions in AdapterObserver
            List<Webservice> lwNews = ao.FiletoWebservices(ao.getNews());
            List<Monitoring> monitors = ao.ListtoMonitors(lwNews);
            List<Analysis> analyzers = ao.ListtoAnalyzers(lwNews);
            List<Planning> planners = ao.ListtoPlanners(lwNews);
            List<Execution> executors = ao.ListtoExecutors(lwNews);
            
            //Set Adapter
            Adapter adapter = new Adapter(monitors, analyzers, planners, executors);
            
            //Set Observers
            adapter.setObserverListAnalysis(monitors);
            adapter.setObserverListPlanning(analyzers);
            adapter.setObserverListExecution(planners);
            
            //Set Specific Functions Observer
            ao.setObserver(adapter);
            
            adapter.run();
    }
}

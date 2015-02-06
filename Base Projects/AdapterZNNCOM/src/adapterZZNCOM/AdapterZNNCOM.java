/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adapterZZNCOM;
import adapter.Adapter;
import adapter.Planning;
import adapter.Monitoring;
import adapter.Analysis;
import java.util.ArrayList;
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
            //Service News
            Webservice wNews = new Webservice("http://com/", "ServiceOneService",
                                              "http://localhost:8080/NewServiceNews/ServiceOne?wsdl",
                                              "ServiceOnePort");
            wNews.setDescription("Service One");
            wNews.setid(1);
            
            Webservice wNews2 = new Webservice("http://com/", "ServiceTwoService",
                                              "http://localhost:8080/NewServiceNews/ServiceTwo?wsdl",
                                              "ServiceTwoPort");
            wNews2.setDescription("Service Two");
            wNews2.setid(2);
            
            Webservice wNews3 = new Webservice("http://com/", "ServiceThreeService",
                                              "http://localhost:8080/NewServiceNews/ServiceThree?wsdl",
                                              "ServiceThreePort");
            wNews3.setDescription("Service Three");
            wNews3.setid(3);
            
            //Useful Functions
            AdapterObserver ao = new AdapterObserver();
               
            //Set Monitors, Analyzers and Planners
            Monitoring monitor = new SpecificMonitor(wNews);    
            Analysis analisador = new SpecificAnalyzer(50);
            Planning planejador = new SpecificPlanner();
            Monitoring monitor2 = new SpecificMonitor(wNews2);    
            Analysis analisador2 = new SpecificAnalyzer(2000);
            Planning planejador2 = new SpecificPlanner();
            Monitoring monitor3 = new SpecificMonitor(wNews3);    
            Analysis analisador3 = new SpecificAnalyzer(3000);
            Planning planejador3 = new SpecificPlanner();
            
            //Configure monitors
            monitor.setValuesMonitor("ValoresMonitor.xml");
            monitor2.setValuesMonitor("ValoresMonitor2.xml"); 
            monitor3.setValuesMonitor("ValoresMonitor3.xml"); 
            
            //Configure analyzers
            analisador.setValuesAnalysis("ValoresAnalise.xml"); 
            analisador.setObserver(monitor);
            analisador2.setValuesAnalysis("ValoresAnalise2.xml"); 
            analisador2.setObserver(monitor2);
            analisador3.setValuesAnalysis("ValoresAnalise3.xml"); 
            analisador3.setObserver(monitor3);
            
            //Configure planners
            planejador.setObserver(analisador);
            planejador2.setObserver(analisador2);
            planejador3.setObserver(analisador3);
            
            //Set List of Monitoring, Analysis and Planning
            List<Monitoring> monitors = new ArrayList<Monitoring>();    
            List<Analysis> analyzers = new ArrayList<Analysis>();
            List<Planning> planners = new ArrayList<Planning>();
            monitors.add(monitor); monitors.add(monitor2); monitors.add(monitor3);
            analyzers.add(analisador); analyzers.add(analisador2); analyzers.add(analisador3);
            planners.add(planejador); planners.add(planejador2); planners.add(planejador3);
            
            //Set Adapter
            Adapter adapter = new Adapter(monitors, analyzers, planners);

            //Set Specific Functions Observer
            ao.setObserver(adapter);

            adapter.run();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ZNNCOM;

import java.io.*;
import webservice.WSFuncs;
import webservice.Webservice;

/**
 *
 * @author Genshiken
 */
public class ZNNCOM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        //Funcionalidades (Serviços)
        Webservice service = new Webservice();
        
        //Weservices funções
        WSFuncs funcs = new WSFuncs();
        
        //ArquivosServiços
        File AllServices = new File("Services.xml");
        if (!AllServices.exists())
            AllServices.createNewFile();
        
        //Interface Gráfica
        ZNNCOMview iAirPort = new ZNNCOMview();
        
        //VariáveisControle
        String aux= "",idservice = "";

       //File used for Stop Criteria of the Adapter
        File sf = new File("StopFile.txt");
        sf.createNewFile();
        try{       
            BufferedWriter out = new BufferedWriter(new FileWriter(sf));
            out.write("");  
            out.flush();
            out.close();
        }catch (IOException e){}
        
        //File used for Stop Criteria of the Adapter
        File uf = new File("UpdateFile.txt");
        uf.createNewFile();
        try{       
            BufferedWriter out = new BufferedWriter(new FileWriter(uf));
            out.write("");  
            out.flush();
            out.close();
        }catch (IOException e){}
        
        //Executing Adapter
        String[] commands = {"","",""};
        commands[0] = "java";
        commands[1] = "-jar";
        commands[2] = "AdapterZNNCOM.jar"; //Path od the GenericAdapterProgram.jar 
        Process p  = Runtime.getRuntime().exec(commands);
        
        //Time to execute Adapter first time
        try {  
           Thread.sleep(2000);  //each getTime() seconds
        } catch (Exception e) {  
           e.printStackTrace();  
        }
  
        while(!idservice.equals("0")){
            
            idservice = iAirPort.WriteinScreen(AllServices);

            if(idservice.equals("0") || idservice.equals("p")){
                if(idservice.equals("p")){
                    //Update Criteria of the Adapter
                    try{       
                        BufferedWriter outs = new BufferedWriter(new FileWriter(uf));
                        outs.write("update");  
                        outs.close();
                    }catch (IOException e){}
                    
                    //Time to execute Adapter first time
                    try {  
                       Thread.sleep(2000);  //each getTime() seconds
                    } catch (Exception e) {  
                       e.printStackTrace();  
                    }
                }
                continue;
            }

            service = funcs.SearchServiceByIDXML(idservice,AllServices);

            if(service!=null){
                aux = funcs.fService(service, service.getarguments().get(0));
            }
            else
                System.out.printf("service not exist, choice other service.\n");

            if(aux != null)
                System.out.println(aux + "\n");
        }
        //Clean Services File
        try{
            BufferedWriter outm = new BufferedWriter(new FileWriter(AllServices));
            outm.write("");  
            outm.flush();
            outm.close();
        }catch (IOException e){} 
        
        //Stop Criteria of the Adapter
        try{       
            BufferedWriter out = new BufferedWriter(new FileWriter(sf));
            out.write("stop");  
            out.close();
        }catch (IOException e){}        
    }
}

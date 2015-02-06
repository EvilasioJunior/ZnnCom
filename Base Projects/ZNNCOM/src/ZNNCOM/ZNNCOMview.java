/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ZNNCOM;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import webservice.Webservice;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 *
 * @author Evilasio
 */
public class ZNNCOMview {
  
    
    public ZNNCOMview(){}
    
    // Informações na tela e entrada de dados do teclado
    public String WriteinScreen(File Valores){
        String idservice;
        Scanner entrada = new Scanner(System.in);
        
        //configurações para leitura xml
        XStream parser = new XStream(new DomDriver());
        parser.setMode(XStream.NO_REFERENCES);
        
        System.out.printf("List of available services:\n");
        System.out.printf("==============================\n");
        
        if(Valores.length()>0){
            ArrayList<Webservice> lsnews = (ArrayList<Webservice>) parser.fromXML(Valores);
            for(Webservice n:lsnews)
                System.out.println("id "+Integer.toString(n.getid())+": "+n.getDescription());
        }
        
        System.out.printf("id 0: Exit\n");
        System.out.printf("Write p to look for update list\n");
        System.out.printf("==============================\n");
        System.out.printf("Inform Id of the desired service\n");

        idservice = entrada.nextLine();
    
        return idservice;
    }
   
}

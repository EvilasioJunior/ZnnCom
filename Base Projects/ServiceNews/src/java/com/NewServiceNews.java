/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.soap.SOAPBinding;

/**
 *
 * @author Evilasio
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class NewServiceNews {
@WebMethod
    public String funcService(String value){                 
        File OutServices = new File("news.html");
        try{ 
            OutServices.createNewFile(); 
            BufferedWriter out = new BufferedWriter(new FileWriter(OutServices));
            out.write(value);  
            out.close();            
        } catch(Exception e){}
            
        //Executing 
        String[] comands = {"",""};
        comands[0] = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe";
        comands[1] = OutServices.getAbsolutePath();
        
        try{
            Runtime.getRuntime().exec(comands);
        }catch(IOException e){}
        
        return "";
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.soap.SOAPBinding;

/**
 *
 * @author Evilasio
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class ServiceOne {
@WebMethod
    public String funcService(String value){                 
        String html = "";
        if(!value.equals("0")){ 
            switch(Integer.parseInt(value)){
                case 1:   html = "<html> \n" + "<head><title>teste</title>\n" + "</head>\n" + "<body>\n" + "teste<br/>\n" +
                                 "teste<p/>\n" + "<img src=\"https://dl.dropboxusercontent.com/u/21609559/fig6.jpg\">\n" + "</body>\n" + "</html>";
                          break;    
                case 2:   html = "<html> \n" + "<head><title>teste</title>\n" + "</head>\n" + "<body>\n" + "teste<br/>\n" +
                                 "teste<p/>\n" + "</body>\n" + "</html>";
                          break;
            }
        }
        return html;
    }
}

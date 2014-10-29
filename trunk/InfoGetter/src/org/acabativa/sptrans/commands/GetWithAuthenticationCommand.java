/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.acabativa.sptrans.commands;

import java.util.HashMap;
import java.util.Map;
import org.acabativa.sptrans.http.GetMachine;

/**
 *
 * @author Matheus
 */
public abstract class GetWithAuthenticationCommand extends Command{

    private static final String COOKIE = "Cookie";
    private String cookieValue;
    private Map<String, String> requestProperties = new HashMap<String, String>();
    private GetMachine getMachine;
    
    
    public GetWithAuthenticationCommand(String cookieValue) {
        this.cookieValue = cookieValue;
        this.requestProperties.put(COOKIE, this.cookieValue);                
    }
    
    private GetMachine getGetterSender(String url, Map<String,String> parameters){
        if(getMachine==null){
            this.getMachine = new GetMachine(url, parameters, this.requestProperties);
        }
        return this.getMachine;
    }
    
    protected void executeGetWithAddress(String address, Map<String,String> parameters){
        GetMachine machine = getGetterSender(address, parameters);
        machine.execute();
    }
    
    protected boolean isDoneWithTheGetter(){
        if(this.getMachine==null){
            return false;
        }
        else{
            return this.getMachine.isDone();
        }
    }
    
    protected String getPayLoadFromGetter(){
        if(this.getMachine==null){
            return null;
        }
        else{
            return this.getMachine.getGetResponse();
        }
    }
    
}

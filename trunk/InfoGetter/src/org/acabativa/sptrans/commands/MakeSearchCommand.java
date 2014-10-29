/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.acabativa.sptrans.commands;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Matheus
 */
public class MakeSearchCommand extends GetWithAuthenticationCommand{
    
    private final String address;
    private final Map<String,String> map = new HashMap<String, String>();
    
    public MakeSearchCommand(String searckKey, String authCookie) {
        super(authCookie);
        this.address = TransMethods.TRANS_URL + TransMethods.SEARCH_LINE;
        this.map.put(TransMethods.SEARCH_LINE_ARG, searckKey);
    }

    @Override
    public void execute() {
        super.executeGetWithAddress(address, map);
    }

    @Override
    public boolean isDone() {
        return super.isDoneWithTheGetter();
    }

    @Override
    public String getPayLoad() {
        return super.getPayLoadFromGetter();
    }
    
    
    
    
}

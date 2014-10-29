/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.acabativa.sptrans.model;

import com.google.gson.Gson;

/**
 *
 * @author Matheus
 */
public class PositionArrayFactory {
    
    private Gson gson = new Gson();
    
    public PositionArray createPositionArray(String json){
        return gson.fromJson(json, PositionArray.class);
    }
    
}

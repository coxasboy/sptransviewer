/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.acabativa.sptrans.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matheus
 */
public class PositionArray {
 
    private String hr;
    
    private List<Position> vs = new ArrayList<Position>();

    public String getHr() {
        return hr;
    }

    public void setHr(String hr) {
        this.hr = hr;
    }

    public List<Position> getVs() {
        return vs;
    }

    public void setVs(List<Position> vs) {
        this.vs = vs;
    }

    @Override
    public String toString() {
        return "PositionArray{" + "hr=" + hr + ", vs=" + vs + '}';
    }
    
}

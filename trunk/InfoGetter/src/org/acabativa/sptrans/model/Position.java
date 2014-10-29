/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.acabativa.sptrans.model;

/**
 *
 * @author Matheus
 */
public class Position {
    
    private Integer p;
    private Boolean a;
    private Double py;
    private Double px;

    public Integer getP() {
        return p;
    }

    public void setP(Integer p) {
        this.p = p;
    }

    public Boolean isA() {
        return a;
    }

    public void setA(Boolean a) {
        this.a = a;
    }

    public Double getPy() {
        return py;
    }

    public void setPy(Double py) {
        this.py = py;
    }

    public Double getPx() {
        return px;
    }

    public void setPx(Double px) {
        this.px = px;
    }

    @Override
    public String toString() {
        return "Position{" + "p=" + p + ", a=" + a + ", py=" + py + ", px=" + px + '}';
    }
    
    
    
}

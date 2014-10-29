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
public class SearchResult {
    
    private Integer CodigoLinha;
    private Boolean Circular;
    private String Letreiro;
    private Integer Tipo;
    private Integer Sentido;
    private String DenominacaoTPTS;
    private String DenominacaoTSTP;
    private String Informações;

    public Integer getCodigoLinha() {
        return CodigoLinha;
    }

    public void setCodigoLinha(Integer CodigoLinha) {
        this.CodigoLinha = CodigoLinha;
    }

    public Boolean isCircular() {
        return Circular;
    }

    public void setCircular(Boolean Circular) {
        this.Circular = Circular;
    }

    public String getLetreiro() {
        return Letreiro;
    }

    public void setLetreiro(String Letreiro) {
        this.Letreiro = Letreiro;
    }

    public Integer getTipo() {
        return Tipo;
    }

    public void setTipo(Integer Tipo) {
        this.Tipo = Tipo;
    }

    public Integer getSentido() {
        return Sentido;
    }

    public void setSentido(Integer Sentido) {
        this.Sentido = Sentido;
    }

    public String getDenominacaoTPTS() {
        return DenominacaoTPTS;
    }

    public void setDenominacaoTPTS(String DenominacaoTPTS) {
        this.DenominacaoTPTS = DenominacaoTPTS;
    }

    public String getDenominacaoTSTP() {
        return DenominacaoTSTP;
    }

    public void setDenominacaoTSTP(String DenominacaoTSTP) {
        this.DenominacaoTSTP = DenominacaoTSTP;
    }

    public String getInformações() {
        return Informações;
    }

    public void setInformações(String Informações) {
        this.Informações = Informações;
    }
    
    
            
            
}

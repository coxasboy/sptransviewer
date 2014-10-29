/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.acabativa.sptrans.commands;

/**
 *
 * @author Matheus
 */
public abstract class Command {
    
    public abstract void execute();
    public abstract boolean isDone();
    public abstract String getPayLoad();
    
}

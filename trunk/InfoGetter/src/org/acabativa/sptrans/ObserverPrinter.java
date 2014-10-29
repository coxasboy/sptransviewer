/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.acabativa.sptrans;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Matheus
 */
public class ObserverPrinter implements Observer{

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(arg);
    }
    
}

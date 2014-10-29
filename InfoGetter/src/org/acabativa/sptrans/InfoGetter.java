/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.acabativa.sptrans;

import java.util.Observer;

/**
 *
 * @author Matheus
 */
public class InfoGetter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String lineId = "689";
        String token = "9cc0a36e67e9f0b342850159ee4a499e04f155c9451daff3ef9be73603caa06d";
        Observer fileSave = new ObserverFileSaver();
        PositionTracker tracker = new PositionTracker(lineId, token);
        tracker.addObserver(fileSave);
        tracker.startTrack();

    }
    
}

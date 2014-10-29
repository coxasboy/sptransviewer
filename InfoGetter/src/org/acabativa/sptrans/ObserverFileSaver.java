/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.acabativa.sptrans;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Observable;
import java.util.Observer;
import org.acabativa.sptrans.model.PositionArray;

/**
 *
 * @author Matheus
 */
public class ObserverFileSaver implements Observer{

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmddss");
    
    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof PositionArray){
            PositionArray position = (PositionArray) arg;
            String fileName = createFileName(position);
            try{
                File file = new File(fileName);
                if(!file.exists()){
                    file.createNewFile();
                }
                System.out.println("+1");
                FileOutputStream fos = new FileOutputStream(file,true);
                fos.write((position.toString()+"\r\n").getBytes());
                fos.close();
            }
            catch(Exception e){
                e.printStackTrace();                        
            }
        }
    }
    
    private String createFileName(PositionArray position){
        return "";//position.getIdentifier() + ".txt";
    }
    
}
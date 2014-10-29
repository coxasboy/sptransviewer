/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.acabativa.sptrans;

import java.util.Date;
import java.util.Observable;
import org.acabativa.sptrans.commands.AuthenticateCommand;
import org.acabativa.sptrans.commands.Command;
import org.acabativa.sptrans.commands.GetPositionCommand;
import org.acabativa.sptrans.model.PositionArray;
import org.acabativa.sptrans.model.PositionArrayFactory;

/**
 *
 * @author Matheus
 */
public class PositionTracker extends Observable implements Runnable{

    private final String lineId;
    private final String token;
    private String currentAuthentication;
    private Date authenticationTime;
    private final long authenticationTimeOut = 1000 * 60 * 30;
    private final long sampleTime = 1000 * 60;
    private final boolean track = true;
    private PositionArrayFactory positionFactory = new PositionArrayFactory();
    
    public PositionTracker(String lineId, String token) {
        this.lineId = lineId;
        this.token = token;
    }
    
    public void startTrack(){
        Thread t = new Thread(this);
        t.start();
    }
    
    @Override
    public void run() {
        while(track){
            try{
                if(mustAuthenticate()){
                    authenticate();
                }
                PositionArray positions = createBusPosition();
                notifyAllListeners(positions);
                Thread.sleep(sampleTime);
            }
            catch(Exception e){
                e.printStackTrace();
            }            
        }
    }
    
    private void notifyAllListeners(PositionArray positions){
        setChanged();
        notifyObservers(positions);        
    }
    
    private boolean mustAuthenticate(){
        if(currentAuthentication==null || authenticationTime==null || (System.currentTimeMillis()-authenticationTime.getTime())>authenticationTimeOut){
            return true;
        }
        return false;
    }
    
    private PositionArray createBusPosition() throws InterruptedException{
        Command getPositionCommand = new GetPositionCommand(lineId, currentAuthentication);
        getPositionCommand.execute();
        while(!getPositionCommand.isDone()){
            Thread.sleep(100);
        }
        String position = getPositionCommand.getPayLoad();
        System.out.println(position);
        PositionArray positionArray = positionFactory.createPositionArray(position);
        System.out.println(positionArray);
        return positionArray;
    }
    
    private void authenticate() throws Exception{
        Command authCommand = new AuthenticateCommand(token);
        authCommand.execute();
        while(!authCommand.isDone()){
            Thread.sleep(100);
        }
        String payLoad = authCommand.getPayLoad();
        if(payLoad==null){
            this.currentAuthentication = null;
            this.authenticationTime = null;
            throw new IllegalAccessException("Could not authenticate");
        }
        else{
            this.currentAuthentication = payLoad;
            this.authenticationTime = new Date();
        }
    }
    
}

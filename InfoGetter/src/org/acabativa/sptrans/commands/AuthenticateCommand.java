/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.acabativa.sptrans.commands;

import org.acabativa.sptrans.http.PostMachine;

/**
 *
 * @author Matheus
 */
public class AuthenticateCommand extends Command{
    
    private String token;
    private PostMachine postMachine;
    private static final String SET_COOKIE = "Set-Cookie";

    public AuthenticateCommand(String token) {
        this.token = token;
        String parameters = TransMethods.AUTHENTICATION_ARG + "=" + token;
        String url = TransMethods.TRANS_URL+TransMethods.AUTHENTICATION + "?" + parameters;
        this.postMachine = new PostMachine(url,parameters);
    }

    @Override
    public void execute() {
        this.postMachine.execute();
    }

    @Override
    public boolean isDone() {
        return this.postMachine.isDone();
    }

    @Override
    public String getPayLoad() {
        return this.postMachine.getHeaderFields().get(SET_COOKIE).get(0);
    }
    
    
}

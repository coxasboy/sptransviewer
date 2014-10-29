/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.acabativa.sptrans.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Matheus
 */
public class PostMachine implements Runnable{
    
    private String url;
    private String parameter;
    private int responseCode = -1;
    private Map<String, List<String>> headerFields;
    private String postResponse;
    private boolean done = false;
    
    public PostMachine(String url, String parameters) {
        this.url = url;
        this.parameter = parameters;        
    }
    
    public void execute(){
        Thread t = new Thread(this);
        t.start();
    }
    
    private void executePost(){
        try{
            URL urlAsObject = new URL(url);
            HttpURLConnection connection = preparePOSTConnection(urlAsObject, parameter.length());
            System.out.println("Connection ok!!!");
            flushParametersThroughtConnection(connection, parameter);
            System.out.println("Parameters flushed");
            this.responseCode = connection.getResponseCode();
            System.out.println("Response code: " + this.responseCode);
            this.headerFields = new HashMap<>(connection.getHeaderFields());
            System.out.println("Headers: " + this.headerFields);
            if(this.responseCode==HttpURLConnection.HTTP_OK){
                this.postResponse = getResponse(connection);
                System.out.println("Post response: " + this.postResponse);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            done = true;
        }
    }
    
    private void flushParametersThroughtConnection(HttpURLConnection connection, String parametersToBeFlushed) throws IOException{
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes(parametersToBeFlushed);
        wr.flush();
        wr.close();
    }
    
    private HttpURLConnection preparePOSTConnection(URL urlAsObject, int parametersSize) throws IOException{
        HttpURLConnection con = (HttpURLConnection) urlAsObject.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestProperty("Content-Length", String.valueOf(parametersSize));
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
        con.setRequestProperty("charset", "utf-8");
        con.setUseCaches (false);
        return con;
    }
    
    private String getResponse(HttpURLConnection connection) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
        }
        return response.toString();
    }

    public String getUrl() {
        return url;
    }

    public String getParameter() {
        return parameter;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public Map<String, List<String>> getHeaderFields() {
        return headerFields;
    }

    public String getPostResponse() {
        return postResponse;
    }

    public boolean isDone() {
        return done;
    }
 
    @Override
    public void run() {
        executePost();
    }
    
    
    
}

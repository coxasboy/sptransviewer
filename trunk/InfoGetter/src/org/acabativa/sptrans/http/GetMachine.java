/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.acabativa.sptrans.http;

import java.io.BufferedReader;
import java.io.IOException;
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
public class GetMachine implements Runnable{
    
    private String url;
    private int responseCode = -1;
    private Map<String, List<String>> headerFields;
    private Map<String, String> properties;
    private Map<String, String> parameters;
    private String getResponse;
    private boolean done = false;
    
    public GetMachine(String url, Map<String, String> parameters) {
        this(url, parameters, null);
    }
    
    public GetMachine(String url, Map<String, String> parameters, Map<String, String> properties) {
        this.url = url;
        this.parameters = parameters; 
        this.properties = properties;
    }
    
    public void execute(){
        Thread t = new Thread(this);
        t.start();
    }
    
    private String prepareUrlString(String url, Map<String, String> parametersForRequest){
        StringBuilder builder = new StringBuilder();
        builder.append(url);
        if(parametersForRequest!=null && !parametersForRequest.isEmpty()){
            builder.append("?");
            for (Map.Entry<String, String> entry : parametersForRequest.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                builder.append(key);
                builder.append("=");
                builder.append(value);
                builder.append("&");
            }
            builder.deleteCharAt(builder.length()-1);
        }
        return builder.toString();
    }
    
    private void executeGet(){
        try{
            String urlWithParameters = prepareUrlString(url, parameters);
            URL urlAsObject = new URL(urlWithParameters);
            HttpURLConnection connection = prepareGETConnection(urlAsObject, properties);
            this.responseCode = connection.getResponseCode();
            this.headerFields = new HashMap<>(connection.getHeaderFields());
            if(responseCode==HttpURLConnection.HTTP_OK){            
                this.getResponse = getResponse(connection);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            done = true;
        }
    }
    
    private HttpURLConnection prepareGETConnection(URL urlAsObject, Map<String,String> specificProperties) throws IOException{
        HttpURLConnection con = (HttpURLConnection) urlAsObject.openConnection();
        con.setRequestMethod("GET");        
        if(specificProperties!=null){
            for (Map.Entry<String, String> entry : specificProperties.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                con.setRequestProperty(key, value);
            }
        }
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

    public Map<String, String> getParameters() {
        return parameters;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public String getGetResponse() {
        return getResponse;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public Map<String, List<String>> getHeaderFields() {
        return headerFields;
    }

    public boolean isDone() {
        return done;
    }
 
    @Override
    public void run() {
        executeGet();
    }
    
    
    
}

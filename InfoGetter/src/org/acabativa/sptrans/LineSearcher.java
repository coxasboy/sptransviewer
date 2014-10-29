/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.acabativa.sptrans;

import java.util.List;
import org.acabativa.sptrans.commands.AuthenticateCommand;
import org.acabativa.sptrans.commands.Command;
import org.acabativa.sptrans.commands.MakeSearchCommand;
import org.acabativa.sptrans.model.SearchResult;
import org.acabativa.sptrans.model.SearchResultFactory;

/**
 *
 * @author Matheus
 */
public class LineSearcher {
    
    public static void main(String[] args) {
        try{
            String searchTerm = "Rosa";
            
            String token = "9cc0a36e67e9f0b342850159ee4a499e04f155c9451daff3ef9be73603caa06d";
            Command authCommand = new AuthenticateCommand(token);
            authCommand.execute();
            while(!authCommand.isDone()){
                Thread.sleep(100);
            }
            String payLoad = authCommand.getPayLoad();
            Command searchCommand = new MakeSearchCommand(searchTerm, payLoad);
            searchCommand.execute();
            while(!searchCommand.isDone()){
                Thread.sleep(100);
            }
            System.out.println(searchCommand.getPayLoad());
            
            SearchResultFactory factory = new SearchResultFactory();
            List<SearchResult> result = factory.createInstanceFromArray(searchCommand.getPayLoad());
            for (SearchResult searchResult : result) {
                System.out.println(searchResult.getCodigoLinha());
                System.out.println(searchResult.getLetreiro());
                System.out.println(searchResult.getSentido());
                System.out.println(searchResult.getTipo());
                System.out.println(searchResult.getInformações());
                System.out.println(searchResult.getDenominacaoTSTP());
                System.out.println(searchResult.getDenominacaoTPTS());
                System.out.println();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}

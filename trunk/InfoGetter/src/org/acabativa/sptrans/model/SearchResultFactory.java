/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.acabativa.sptrans.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

/**
 *
 * @author Matheus
 */
public class SearchResultFactory {
    
    private Gson gson = new Gson();
    
    public SearchResult createInstance(String searchResult){
        SearchResult result = gson.fromJson(searchResult, SearchResult.class);
        return result;
    }
    
    public List<SearchResult> createInstanceFromArray(String searchResult){
        Type collectionType = new TypeToken<List<SearchResult>>(){}.getType();
        List<SearchResult> result = gson.fromJson(searchResult, collectionType);
        return result;
    }
    
}

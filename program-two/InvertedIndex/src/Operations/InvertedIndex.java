
package Operations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 *
 * @author mohamed
 */
public class InvertedIndex {
    private HashMap<String,LinkedHashSet<Document>> index;
    private ArrayList<String> queryTokens;
    
    public InvertedIndex(){
        index = new HashMap<>();
        queryTokens = new ArrayList<>();
    }
    
    public void buildIndex(Document doc){
        for(String token : doc.getTokens()){
            if(!index.containsKey(token)){
                //case new token
                LinkedHashSet<Document> occurences = new LinkedHashSet<>();
                occurences.add(doc);
                index.put(token , occurences);
            } else{
                //case token exists add the other document it appears in to existing docs
                index.get(token).add(doc);
            }
        }
    }
    
    public HashMap<String , LinkedHashSet<Document>> getQueryPostingList(){
        HashMap<String , LinkedHashSet<Document>> postingList = new HashMap<>();
        queryTokens.forEach((token)-> {
            postingList.put(token , index.getOrDefault(token , new LinkedHashSet<>()));
        });
        
        return postingList;
    }
    
    public String manipulateQuery(String query){
        query = query.toLowerCase().replaceAll(" & ", " && ").replaceAll(" | ", " || ");
        for(String token : queryTokens){
            query =  query.replaceAll(token, String.join("," , index.getOrDefault(token,new LinkedHashSet<>()).toString()));
        }
        query = query.replaceAll("\\[", "").replaceAll("\\]", "");
        return query;
    }
    
    
}


package task2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 *
 * @author mohamed
 */
public class InvertedIndex {
    private LinkedHashSet<Document> postings;//to maintain the order of insertion when retrieving items
    private HashMap<String,LinkedHashSet<Document>> index;
    private ArrayList<String> queryTokens;
    
    public InvertedIndex(){
        postings = new LinkedHashSet<>();
        index = new HashMap<>();
        queryTokens = new ArrayList<>();
    }
    
    public void buildIndex(Document doc){
        postings.add(doc);
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
    
    
}

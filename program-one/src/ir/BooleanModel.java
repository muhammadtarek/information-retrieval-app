/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 *
 * @author medo_
 */
public class BooleanModel {

    Map<String, String> matrix;
    HashSet<String> Tokens;
    String VectorResult;
    static ArrayList<String> StopWords = new ArrayList<>(Arrays.asList(new String[]{"a", "an", "if", "the", "we", "and", "or", "about", "above", "across", "after", "all", "also", "although",
        "another", "any", "am", "is", "are", "at", "be", "as", "at", "but", "by", "do", "where"}));

    public BooleanModel() {
        matrix = new HashMap<>();
        Tokens = new HashSet<>();

    }

    

    public void setTokens(List<Document> Docs)
    {
        for (Document Doc : Docs) {
            ArrayList<String> tmp = Doc.getTokens();
            tmp.removeAll(StopWords);
            Tokens.addAll(tmp);
        }
    }
    
     

    public Map<String, String> getMatrix(List<Document> Docs) {
        setTokens(Docs);
        for (String Token : Tokens) {
            String Vector = "";
            for (Document Doc : Docs) {
                Vector += Doc.getText().contains(Token) ? 1 : 0;
            }
            matrix.put(Token, Vector);
        }
        return matrix;

    }

    public String ManipulateQuery(String query) {

        for (Map.Entry<String, String> entry : matrix.entrySet()) {
            query = query.replaceAll(entry.getKey(), entry.getValue());
            //System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        query=query.toLowerCase().replaceAll("and", "&").replaceAll("or", "|").replaceAll("not", "!");
        return query;
    }
    
    
    public ArrayList<Document> GetDocumnetsNames(ArrayList<Document> Docs)
    {
        ArrayList<Document> result = new ArrayList<>();
        for (int i = 0; i < VectorResult.length(); i++) {
            if (VectorResult.charAt(i) == '1') {
                result.add(Docs.get(i));
            }
        }
        return result;
    }
    

    public String QueryResult(ArrayList<Document> Docs, String query) {
     
            query = ManipulateQuery(query);
            System.out.println(query);
            VectorResult = SimpleBooleanEvaluator.booleanResult(query);
            System.out.println(VectorResult);
        
        return VectorResult;
    }
}

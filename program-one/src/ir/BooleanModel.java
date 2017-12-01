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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author medo_
 */
public class BooleanModel {

    Map<String, String> matrix;
    HashSet<String> Tokens;
    String VectorResult;
    static ArrayList<String> StopWords = new ArrayList<>(Arrays.asList(new String[]{"a", "about", "above", "above", "across", "after", "afterwards", "again", "against", "all", "almost", "alone", "along", "already", "also", "although", "always", "am", "among", "amongst", "amoungst", "amount", "an", "and", "another", "any", "anyhow", "anyone", "anything", "anyway", "anywhere", "are", "around", "as", "at", "back", "be", "became", "because", "become", "becomes", "becoming", "been", "before", "beforehand", "behind", "being", "below", "beside", "besides", "between", "beyond", "bill", "both", "bottom", "but", "by", "call", "can", "cannot", "cant", "co", "con", "could", "couldnt", "cry", "de", "describe", "detail", "do", "done", "down", "due", "during", "each", "eg", "eight", "either", "eleven", "else", "elsewhere", "empty", "enough", "etc", "even", "ever", "every", "everyone", "everything", "everywhere", "except", "few", "fifteen", "fify", "fill", "find", "fire", "first", "five", "for", "former", "formerly", "forty", "found", "four", "from", "front", "full", "further", "get", "give", "go", "had", "has", "hasnt", "have", "he", "hence", "her", "here", "hereafter", "hereby", "herein", "hereupon", "hers", "herself", "him", "himself", "his", "how", "however", "hundred", "ie", "if", "in", "inc", "indeed", "interest", "into", "is", "it", "its", "itself", "keep", "last", "latter", "latterly", "least", "less", "ltd", "made", "many", "may", "me", "meanwhile", "might", "mill", "mine", "more", "moreover", "most", "mostly", "move", "much", "must", "my", "myself", "name", "namely", "neither", "never", "nevertheless", "next", "nine", "no", "nobody", "none", "noone", "nor", "not", "nothing", "now", "nowhere", "of", "off", "often", "on", "once", "one", "only", "onto", "or", "other", "others", "otherwise", "our", "ours", "ourselves", "out", "over", "own", "part", "per", "perhaps", "please", "put", "rather", "re", "same", "see", "seem", "seemed", "seeming", "seems", "serious", "several", "she", "should", "show", "side", "since", "sincere", "six", "sixty", "so", "some", "somehow", "someone", "something", "sometime", "sometimes", "somewhere", "still", "such", "system", "take", "ten", "than", "that", "the", "their", "them", "themselves", "then", "thence", "there", "thereafter", "thereby", "therefore", "therein", "thereupon", "these", "they", "thickv", "thin", "third", "this", "those", "though", "three", "through", "throughout", "thru", "thus", "to", "together", "too", "top", "toward", "towards", "twelve", "twenty", "two", "un", "under", "until", "up", "upon", "us", "very", "via", "was", "we", "well", "were", "what", "whatever", "when", "whence", "whenever", "where", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which", "while", "whither", "who", "whoever", "whole", "whom", "whose", "why", "will", "with", "within", "without", "would", "yet", "you", "your", "yours", "yourself", "yourselves", "the"}));

    public BooleanModel() {
        matrix = new HashMap<>();
        Tokens = new HashSet<>();

    }

    public void setTokens(List<Document> Docs) {
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
                Vector += isContain(Doc.getText().toLowerCase(),Token) ? 1 : 0;
            }
            matrix.put(Token, Vector);
        }
        return matrix;

    }

    public String ManipulateQuery(String query) {
        String empty = matrix.entrySet().iterator().next().getValue().replace("1", "0");
        for (String x : Arrays.asList(query.toLowerCase().replaceAll("\\band\\b", " ").replaceAll("\\bor\\b", " ").replaceAll("\\bnot\\b", " ").replaceAll("\\(", "").replaceAll("\\)", "").trim().split("\\s+"))) {
            if (!matrix.containsKey(x)) {
                System.out.println("=========> "+x);
                query = query.replace(x, empty);
            }
        }
        System.out.println(query);
        for (Map.Entry<String, String> entry : matrix.entrySet()) {
            query = query.replaceAll("\\b"+entry.getKey()+"\\b", entry.getValue());
            System.out.println(query);
            //System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println(query);
        query = query.toLowerCase().replaceAll("\\band\\b", " & ").replaceAll("\\bor\\b", " | ").replaceAll("\\bnot\\b", " ! ");
        System.out.println(query);
        return query;
    }

    public ArrayList<Document> GetDocumnetsNames(ArrayList<Document> Docs) {
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
        //System.out.println(query);
        VectorResult = SimpleBooleanEvaluator.booleanResult(query);
        //System.out.println(VectorResult);
        return query + " = " + VectorResult;
    }
    
    private static boolean isContain(String source, String subItem) {
        String pattern = "\\b" + subItem + "\\b";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(source);
        return m.find();
    }
}

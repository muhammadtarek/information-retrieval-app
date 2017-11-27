
package task2;

import java.util.*;
/**
 *
 * @author mohamed
 */
public class Document {
    private String docName;
    private String docContent;
    private static final ArrayList<String> STOPWORDS = new ArrayList<>(Arrays.asList(new String[]{"a", 
        "an", "if", "the", "we", "and", "or", "about", "above", "across", "after", "all", "also",
        "although","another", "any", "am", "is", "are", "at", "be", "as", "at", "but", "by", "do", "where"}));
    
    public Document(String name , String content){
        docName = name;
        docContent = content;
    }

    public String getDocName() {
        return docName;
    }

    public String getDocContent() {
        return docContent;
    }
    
    public ArrayList<String> getTokens(){
        //convert docContent to lower case , trim it , split around white spaces to get tokens.
        ArrayList<String> tokens = new ArrayList<>(Arrays.asList(docContent.toLowerCase().trim().split("\\s+")));
        tokens.removeAll(STOPWORDS);
        return tokens;
    }
    
    @Override
    public String toString(){
        //return "document{name = " + docName + ", content = " + docContent + '}';
        return docName;
    }
    
    
    
    
}

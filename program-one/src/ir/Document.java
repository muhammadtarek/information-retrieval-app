/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author mosta
 */
public class Document {
    private String name;
    private String text;
    public Document(String name , String text) {
        this.name = name;
        this.text = text;
    }
    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }
    
    
    public ArrayList<String> getTokens()
    {
        ArrayList<String> Tokens = new ArrayList<>(Arrays.asList(this.text.toLowerCase().trim().split("\\s+")));
        Tokens.removeAll(BooleanModel.StopWords);
        return new ArrayList<>(Tokens);
    }

    @Override
    public String toString() {
        return "document{" + "name=" + name + ", text=" + text + '}';
    }
}

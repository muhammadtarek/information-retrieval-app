package ir;

import static java.lang.Boolean.TRUE;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class IR {

    public static List AllWordTemp;
    public static HashMap<String, HashMap<String, HashSet<Integer>>> tokens;
    public static String[] query = null;
    public static HashMap<String, ArrayList<String>> allDocs = new HashMap<String, ArrayList<String>>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList Documets = new ArrayList();
        tokens = new HashMap<String, HashMap<String, HashSet<Integer>>>();
        String concatenatedDocs = "";
        String x = "";
        int i;

        System.out.print("Enter Number of documents : ");
        int docNumber = sc.nextInt();
        x = sc.nextLine();
        for (i = 0; i < docNumber; i++) {
            Documets.add(sc.nextLine());
            concatenatedDocs = concatenatedDocs + Documets.get(i) + " - ";
        }
        concatenatedDocs = concatenatedDocs.substring(0, concatenatedDocs.length() - 3);
        //System.out.print(concatenatedDocs.split("-| ")[0]);
        System.out.print("Enter Your Query : ");
        x = sc.nextLine();
        x = x.toLowerCase();
        AllWordTemp = Arrays.asList(concatenatedDocs.split(" "));
        query = x.split(" ");
        for (String word : query) {
            tokens.put(word, rightPart(word));
        }
        System.out.println(Arrays.asList(tokens));
        System.out.println("-------------------------");
        getValidDoc();

    }

    public static HashMap<String, HashSet<Integer>> rightPart(String word) {
        HashMap<String, HashSet<Integer>> subHashMap = new HashMap<String, HashSet<Integer>>();
        HashSet<Integer> places = new HashSet<Integer>();
        int i;
        int wordIndex = 1;
        int docNumber = 1;

        for (i = 0; i < AllWordTemp.size(); i++) {
            if (AllWordTemp.get(i).equals(word)) {
                places.add(wordIndex);
                wordIndex++;
                subHashMap.put("Doc(" + docNumber + ")", places);
            } else if (AllWordTemp.get(i).equals("-")) {
                places = new HashSet<Integer>();
                wordIndex = 1;
                docNumber++;
            } else if (!AllWordTemp.get(i).equals(word)) {
                wordIndex++;
            }
        }
        return subHashMap;
    }

    public static void getValidDoc() {
        HashMap<String, HashSet<Integer>> subHashMap1 = new HashMap<String, HashSet<Integer>>();
        HashMap<String, HashSet<Integer>> subHashMap2 = new HashMap<String, HashSet<Integer>>();
        HashSet<Integer> places1 = new HashSet<Integer>();
        HashSet<Integer> places2 = new HashSet<Integer>();
        HashSet<String> validDocs = new HashSet<String>();
        List<String> firstWordDocuments = null; // intersect document will be stored in it
        List<String> secondWordDocuments = null;
        ArrayList<Boolean> boolList = null;
        int i = 0;
        for (i = 0; i < query.length - 1; i++) // get intersection documents
        {
            subHashMap1 = tokens.get(query[i]);
            subHashMap2 = tokens.get(query[i + 1]);
            firstWordDocuments = new ArrayList<String>(subHashMap1.keySet());
            secondWordDocuments = new ArrayList<String>(subHashMap2.keySet());
            firstWordDocuments.retainAll(secondWordDocuments); // intersection documents 
        }
        for (String docName : firstWordDocuments) {
            boolList = new ArrayList<>();
            for (i = 0; i < query.length - 1; i++) {
                subHashMap1 = tokens.get(query[i]);
                subHashMap2 = tokens.get(query[i + 1]);
                places1 = subHashMap1.get(docName);
                places2 = subHashMap2.get(docName);
                //("subHashMap1 --> " + subHashMap1);
                //("places 1 --> " + places1 + " -- " + docName);
                if (places1 != null) {
                    for (int index : places1) {
                        index = index + 1;
                        if (places2.contains(index)) {
                            boolList.add(true);
                        } else {
                            boolList.add(false);
                        }
                    }
                }
                //System.out.println("******** "+docName + " -- " + boolList);
                if (Collections.frequency(boolList, true) == (query.length - 1)) {
                    validDocs.add(docName);
                }
            }
        }

        System.out.println("VALID DOCS -- > " + validDocs);
    }

}

/*
 mohd mohd khairala
 mohd kmal mohd khairala
 ali mohd
 magdy mohd khairala
 ahmed yaser

 new home sales top forecasts new home sales rise
 home home sales rise in july new home new home new home
 increase in home sales in july
 july new home sales rise

 a b c e d
 x r q g e
 a b c d g
 w b t w q

 a b c d e f
 b c e f g
 a b c d e g
 b c d e j

 */

package ir;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import javax.print.DocFlavor;

public class IR {

    public static List AllWordTemp;
    public static HashMap<String, HashMap<String, HashSet<Integer>>> tokens;
    public static String[] query = null;
    public static HashMap<String, ArrayList<String>> allDocs = new HashMap<>();
    public static HashSet<String> validDocs = new HashSet<>();

    public static String positionIndex(String queryGui) throws UnsupportedEncodingException, IOException {
        ArrayList Documets = new ArrayList();
        tokens = new HashMap<>();
        String concatenatedDocs = "";
        String x = "";
        int i;

        String target_dir = "docs/";
        File dir = new File(target_dir);
        File[] files = dir.listFiles();

        for (File f : files) { //Read each file in string
            if (f.isFile()) {
                FileInputStream inputStream;
                inputStream = new FileInputStream(f);
                String line;
                byte[] data = new byte[(int) f.length()];
                inputStream.read(data);
                inputStream.close();

                String str = new String(data, "UTF-8");
                Documets.add(str.replace("\n", "").replace("\r", " ")); // /r & /n for windows
                concatenatedDocs = concatenatedDocs + Documets.get((Documets.size() - 1)) + " - "; // - is used to seperate documents later
            }
        }
        concatenatedDocs = concatenatedDocs.substring(0, concatenatedDocs.length() - 3); // delete last - with 2 spaces
        System.out.println(concatenatedDocs.toLowerCase());
        AllWordTemp = Arrays.asList(concatenatedDocs.toLowerCase().split(" "));
        query = queryGui.split(" ");
        for (String word : query) {
            tokens.put(word, rightPart(word));
        }
        System.out.println(Arrays.asList(tokens).toString());
        System.out.println("-------------------------");
        return getValidDoc();
    }

    public static HashMap<String, HashSet<Integer>> rightPart(String word) {
        HashMap<String, HashSet<Integer>> subHashMap = new HashMap<>();
        HashSet<Integer> places = new HashSet<>();
        int i;
        int wordIndex = 1;
        int docNumber = 1;

        for (i = 0; i < AllWordTemp.size(); i++) {
            if (AllWordTemp.get(i).equals(word)) { //put char index
                places.add(wordIndex);
                wordIndex++;
                subHashMap.put("Doc(" + docNumber + ")", places);
            } else if (AllWordTemp.get(i).equals("-")) { // if start second document
                places = new HashSet<Integer>();
                wordIndex = 1;
                docNumber++;
            } else if (!AllWordTemp.get(i).equals(word)) {
                wordIndex++;
            }
        }
        return subHashMap;
    }

    public static String getValidDoc() {
        HashMap<String, HashSet<Integer>> subHashMap1 = new HashMap<>();
        HashMap<String, HashSet<Integer>> subHashMap2 = new HashMap<>();
        HashSet<Integer> places1 = new HashSet<>();
        HashSet<Integer> places2 = new HashSet<>();

        List<String> firstWordDocuments = null; // intersect document will be stored in it
        List<String> secondWordDocuments = null;
        ArrayList<Boolean> boolList = null;
        int i = 0;
        for (i = 0; i < query.length - 1; i++) // get intersection documents
        {
            subHashMap1 = tokens.get(query[i]);
            subHashMap2 = tokens.get(query[i + 1]);
            firstWordDocuments = new ArrayList<>(subHashMap1.keySet());
            secondWordDocuments = new ArrayList<>(subHashMap2.keySet());
            firstWordDocuments.retainAll(secondWordDocuments); // intersection documents 
        }
        if (firstWordDocuments != null) {
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
                    if (Collections.frequency(boolList, true) >= (query.length - 1)) {
                        validDocs.add(docName);
                    }
                }
            }
        }
        return validDocs.toString();
    }
}

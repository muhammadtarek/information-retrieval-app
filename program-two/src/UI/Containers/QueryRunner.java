package UI.Containers;

import Operations.Document;
import UI.Components.Title;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class QueryRunner {

  private static QueryRunner instance;
  private static Tokens matchedDocuments;
  private static GridPane tokensList;

  private GridPane queryRunnerContainer;

  private QueryRunner() {

    this.render();
  }

  private void render() {
    // Matched document title
    Title matchedDocumentTitle = new Title("Matched documents");
    GridPane.setConstraints(matchedDocumentTitle.getTitle(), 0, 0);
    GridPane.setMargin(matchedDocumentTitle.getTitle(), new Insets(30, 0 , 30, 0));

    // Matched documents
    matchedDocuments = new Tokens();
    GridPane.setConstraints(matchedDocuments.getTokensContainer(), 0, 1);
    
    // Tokens list title
    Title tokensListTitle = new Title("Posting lists");
    GridPane.setConstraints(tokensListTitle.getTitle(), 0, 2);

    // Tokens list
    tokensList = new GridPane();
    GridPane.setConstraints(tokensList, 0, 3);
    tokensList.setVgap(20);
    tokensList.setHgap(30);

    // Query runner container
    queryRunnerContainer = new GridPane();
    queryRunnerContainer.getChildren().addAll(
        matchedDocumentTitle.getTitle(),
        matchedDocuments.getTokensContainer(),
        tokensListTitle.getTitle(),
        tokensList);
  }

  public static void loadMatchedDocuments(String document) {
    ArrayList<String> tokens = new ArrayList<>();
    tokens.add(document);
    matchedDocuments.setTokens(tokens);
  }

  public static void loadTokensList(HashMap<String , LinkedHashSet<Document>> tokens) {
    tokensList.getChildren().clear();
    int counter = 0;
    for (Map.Entry<String , LinkedHashSet<Document>> token : tokens.entrySet()) {
      Label documentName = new Label(token.getKey());
      GridPane.setConstraints(documentName, 0, counter);

      Label documentsNumber = new Label(Integer.toString(token.getValue().size()));
      GridPane.setConstraints(documentsNumber, 1, counter);

      ArrayList<String> documents = new ArrayList<>();

      for (Document document : token.getValue()) {
        documents.add(document.getDocName());
      }

      Tokens matchedDocuments = new Tokens();
      matchedDocuments.setTokens(documents);
      GridPane.setConstraints(matchedDocuments.getTokensContainer(), 2 , counter);

      tokensList.getChildren().addAll(documentName, documentsNumber, matchedDocuments.getTokensContainer());
      counter++;
    }
  }

  public GridPane getQueryRunnerContainer() {
    return queryRunnerContainer;
  }

  public static QueryRunner getInstance() {
    if (instance == null) {
      instance = new QueryRunner();
    }

    return instance;
  }
}

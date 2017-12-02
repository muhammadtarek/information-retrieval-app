package UI.Components;

import Operations.InvertedIndex;
import Operations.SimpleBooleanEvaluator;
import UI.App;
import UI.Containers.QueryRunner;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchBar {

  private TextField search;
  private InvertedIndex invertedIndex;

  public SearchBar() {
    invertedIndex = InvertedIndex.getInstance();
    this.render();
  }

  private void render() {
    search = new TextField();
    search.setPromptText("Enter your query here...");
    search.getStyleClass().add("search-bar");
    search.setOnKeyReleased(e ->
    {
      QueryRunner.clearMatchedDocuments();
      String searchQuery = search.getText();
      searchQuery = searchQuery.replaceAll("[()]", "").toLowerCase();
      List<String> myList = new ArrayList<String>(Arrays.asList(searchQuery.split(" ")));
      invertedIndex.setQueryTokens((ArrayList<String>) myList);     
      App.showQueryRunner();
      
      QueryRunner.loadTokensList(invertedIndex.getQueryPostingList());


      if (e.getCode().equals(KeyCode.ENTER))
      {
        String matchedDocuments = invertedIndex.manipulateQuery(searchQuery);
        if (matchedDocuments != null) {
          QueryRunner.loadMatchedDocuments(SimpleBooleanEvaluator.booleanResult(matchedDocuments));
        }
      }
    });
  }

  public TextField getSearchBar() {
    return this.search;
  }

}

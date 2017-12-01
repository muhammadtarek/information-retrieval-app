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
      String searchQuery = search.getText();

      List<String> myList = new ArrayList<String>(Arrays.asList(searchQuery.split(" ")));
      invertedIndex.setQueryTokens((ArrayList<String>) myList);
      App.showQueryRunner();
      QueryRunner.loadTokensList(invertedIndex.getQueryPostingList());


      if (e.getCode().equals(KeyCode.ENTER))
      {
        QueryRunner.loadMatchedDocuments(SimpleBooleanEvaluator.booleanResult(invertedIndex.manipulateQuery(searchQuery)));
      }
    });
  }

  public TextField getSearchBar() {
    return this.search;
  }

}

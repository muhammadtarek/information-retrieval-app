package UI.Components;

import UI.App;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class SearchBar {

  private TextField search;

  public SearchBar() {
    this.render();
  }

  private void render() {
    search = new TextField("Enter your query...");
    search.getStyleClass().add("search-bar");
    search.setOnKeyPressed(e ->
    {
      if (e.getCode().equals(KeyCode.ENTER))
      {
        App.showQueryRunner();
      }
    });
  }

  public TextField getSearchBar() {
    return this.search;
  }

}

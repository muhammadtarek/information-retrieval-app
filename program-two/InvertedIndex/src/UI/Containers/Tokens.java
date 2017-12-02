package UI.Containers;

import UI.Components.Token;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

class Tokens {

  private ArrayList<String> tokens;
  private GridPane tokensContainer;

  Tokens() {
    this.render();
  }

  private void render() {
    tokensContainer = new GridPane();
    tokensContainer.setHgap(10);
  }

  private void loadTokens() {
    int counter = 0;
    tokensContainer.getChildren().clear();
    for (String token : tokens) {
      Token newToken = new Token(token);
      GridPane.setConstraints(newToken.getTokenView(), counter, 0);
      counter++;
      tokensContainer.getChildren().add(newToken.getTokenView());
    }
  }

  void setTokens(ArrayList<String> tokens) {
    this.tokens = tokens;
    this.loadTokens();
  }

  void clear() {
    tokensContainer.getChildren().clear();
  }

  GridPane getTokensContainer() {
    return tokensContainer;
  }
}

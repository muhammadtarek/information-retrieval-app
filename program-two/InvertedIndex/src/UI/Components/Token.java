package UI.Components;

import javafx.scene.control.Label;

public class Token {

  private String token;
  private Label tokenView;

  public Token(String token) {
    this.token = token;
    this.render();
  }

  private void render() {
    tokenView = new Label(this.token);
    tokenView.getStyleClass().add("token");
  }

  public Label getTokenView() {
    return tokenView;
  }
}

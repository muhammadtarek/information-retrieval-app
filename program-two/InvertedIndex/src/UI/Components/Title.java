package UI.Components;

import javafx.scene.control.Label;

public class Title {

  private String title;
  private Label label;

  public Title() {
    this.render();
  }

  public Title(String title) {
    this.title = title;
    this.render();
  }

  private void render() {
    label = new Label(this.title);
    label.getStyleClass().add("title");
  }

  public Label getTitle() {
    return label;
  }

  public void setTitle(String newTitle) {
    label.setText(newTitle);
  }
}

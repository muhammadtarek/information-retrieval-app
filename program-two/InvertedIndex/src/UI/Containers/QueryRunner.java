package UI.Containers;

import javafx.scene.layout.GridPane;

public class QueryRunner {

  private static QueryRunner instance;
  private GridPane queryRunnerContainer;

  private QueryRunner() {
    this.render();
  }

  private void render() {

    // Query runner container
    queryRunnerContainer = new GridPane();
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

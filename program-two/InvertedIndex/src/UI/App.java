package UI;

import UI.Components.SearchBar;
import UI.Containers.DocsNavigator;
import UI.Containers.DocumentDetails;
import UI.Containers.QueryRunner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class App extends Application{

  private GridPane app;
  private static GridPane documentDetails;
  private static GridPane queryRunner;
  private static GridPane detailsPane;

  @Override
    public void start(Stage primaryStage) {
        GridPane docsNavigator = DocsNavigator.getInstance().getDocsNavigator();
        GridPane.setConstraints(docsNavigator, 0, 0);

        SearchBar searchBar = new SearchBar();
        GridPane.setConstraints(searchBar.getSearchBar(), 0, 0);

        documentDetails = DocumentDetails.getInstance().getDocumentDetailsContainer();
        GridPane.setConstraints(documentDetails, 0, 1);

        queryRunner = QueryRunner.getInstance().getQueryRunnerContainer();
        GridPane.setConstraints(queryRunner, 0, 1);

        detailsPane = new GridPane();
        GridPane.setConstraints(detailsPane, 1, 0);
        detailsPane.getRowConstraints().addAll(new RowConstraints(), new RowConstraints());
        detailsPane.getRowConstraints().get(0).setPercentHeight(10);
        detailsPane.getRowConstraints().get(1).setPercentHeight(90);
        detailsPane.getColumnConstraints().add(new ColumnConstraints());
        detailsPane.getColumnConstraints().get(0).setPercentWidth(100);
        detailsPane.setPadding(new Insets(30));
        detailsPane.getChildren().addAll(searchBar.getSearchBar());

        RowConstraints fullHeight = new RowConstraints();
        fullHeight.setPercentHeight(100);

        ColumnConstraints docsColumn = new ColumnConstraints();
        docsColumn.setPercentWidth(30);

        ColumnConstraints detailsColumn = new ColumnConstraints();
        detailsColumn.setPercentWidth(70);

        //App container
        app = new GridPane();
        app.getStyleClass().add("app--container");
        app.getRowConstraints().add(fullHeight);
        app.getColumnConstraints().addAll(docsColumn, detailsColumn);
        app.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> app.requestFocus());
        app.getChildren().addAll(docsNavigator, detailsPane);

        //App Scene
        Scene scene = new Scene(app, 1200, 600);
        primaryStage.setTitle("Program Two");
        primaryStage.setMinWidth(1200);
        primaryStage.setMinHeight(600);

        //Importing CSS Files
        scene.getStylesheets().add("/UI/style.css");

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showDocumentDetails() {
      detailsPane.getChildren().remove(queryRunner);
      detailsPane.getChildren().remove(documentDetails);
      detailsPane.getChildren().add(documentDetails);
    }

    public static void showQueryRunner() {
      detailsPane.getChildren().remove(documentDetails);
      detailsPane.getChildren().remove(queryRunner);
      detailsPane.getChildren().add(queryRunner);
    }
}
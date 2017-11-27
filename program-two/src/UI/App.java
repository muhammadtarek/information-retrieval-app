package UI;

import UI.Containers.DocsNavigator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class App extends Application{

    private GridPane app;

    @Override
    public void start(Stage primaryStage) {
        GridPane docsNavigator = DocsNavigator.getInstance().getDocsNavigator();
        GridPane.setConstraints(docsNavigator, 0, 0);

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
        app.getChildren().addAll(docsNavigator);

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
}
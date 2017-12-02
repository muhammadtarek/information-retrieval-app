package UI.Containers;

import UI.Components.Document;
import UI.Components.Title;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DocsNavigator {

    private static DocsNavigator instance;
    private ArrayList<Document> documents;
    private FileChooser fileChooser;
    private List<File> files;
    private GridPane docsList;
    private GridPane docsNavigatorContainer;

    private  DocsNavigator() {
        this.render();
    }

    private void render() {
        // Document title
        Title docsTitle = new Title("Documents");
        GridPane.setConstraints(docsTitle.getTitle(), 0, 0);

        // Upload document button
        Button uploadButton = new Button("Upload");
        uploadButton.getStyleClass().add("docs-navigation--button");
        uploadButton.setAlignment(Pos.BASELINE_RIGHT);
        uploadButton.setOnAction(e -> {
            try {
                this.chooseNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        GridPane.setConstraints(uploadButton, 1, 0);

        // File chooser
        fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a file!");

        FileChooser.ExtensionFilter fileExtensions =
            new FileChooser.ExtensionFilter("Text Files", "*.txt");

        fileChooser.getExtensionFilters().add(fileExtensions);

        // Header container
        GridPane headerContainer = new GridPane();

        headerContainer.getColumnConstraints()
            .addAll(new ColumnConstraints(), new ColumnConstraints());
        headerContainer.getColumnConstraints().get(0).setPercentWidth(60);
        headerContainer.getColumnConstraints().get(1).setPercentWidth(40);
        headerContainer.getColumnConstraints().get(1).setHalignment(HPos.RIGHT);

        headerContainer.setPadding(new Insets(30));
        headerContainer.getChildren().addAll(docsTitle.getTitle(), uploadButton);
        GridPane.setConstraints(headerContainer, 0, 0);

        // Docs list container
        documents = new ArrayList<>();
        docsList = new GridPane();
        docsList.setPadding(new Insets(0, 30, 30, 30));
        docsList.setVgap(10);
        for (Document document : documents) {
            GridPane.setConstraints(document.getDocumentLabel(), 0, documents.size());
            docsList.getChildren().add(document.getDocumentLabel());
        }
        GridPane.setConstraints(docsList, 0, 1);

        // Docs navigator container
        docsNavigatorContainer = new GridPane();
        docsNavigatorContainer.getColumnConstraints().add(new ColumnConstraints());
        docsNavigatorContainer.getColumnConstraints().get(0).setPercentWidth(100);
        docsNavigatorContainer.getStyleClass().add("docs-navigation--container");
        docsNavigatorContainer.getChildren().addAll(headerContainer, docsList);
    }

    private void addNewDocument(String name, String data) {
        Document newDocument = new Document(name, data);
        documents.add(newDocument);
        GridPane.setConstraints(newDocument.getDocumentLabel(), 0, documents.size());
        docsList.getChildren().add(newDocument.getDocumentLabel());
    }

    private void chooseNewFile() throws IOException {
        files = fileChooser.showOpenMultipleDialog(null);

        if (files != null) {
            for (File file : files) {

                try (BufferedReader br = new BufferedReader(new FileReader(file.getAbsoluteFile()))) {
                    StringBuilder sb = new StringBuilder();
                    String line = br.readLine();

                    while (line != null) {
                        sb.append(line);
                        sb.append(System.lineSeparator());
                        line = br.readLine();
                    }
                    String fileContent = sb.toString();
                    this.addNewDocument(file.getName(), fileContent);
                }
            }

        }
    }

    public GridPane getDocsNavigator() {
        return this.docsNavigatorContainer;
    }

    public static DocsNavigator getInstance() {
        if (instance == null) {
            instance = new DocsNavigator();
        }
        return instance;
    }
}

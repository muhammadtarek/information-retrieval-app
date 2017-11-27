package UI.Containers;

import UI.Components.Document;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.ArrayList;

public class DocsNavigator {

    private static DocsNavigator instance;
    private ArrayList<Document> documents;
    private FileChooser fileChooser;
    private File file;
    private GridPane docsList;
    private GridPane docsNavigatorContainer;

    private  DocsNavigator() {
        this.render();
    }

    private void render() {
        // Document title
        Label docsTitle = new Label("Documents");
        docsTitle.getStyleClass().add("docs-navigation--title");
        docsTitle.setAlignment(Pos.BASELINE_LEFT);
        GridPane.setConstraints(docsTitle, 0, 0);

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
        headerContainer.setPadding(new Insets(30));
        headerContainer.getChildren().addAll(docsTitle, uploadButton);
        GridPane.setConstraints(headerContainer, 0, 0);

        // Docs list container
        documents = new ArrayList<>();
        docsList = new GridPane();
        for (Document document : documents) {
            GridPane.setConstraints(document.getDocumentLabel(), 0, documents.size());
            docsList.getChildren().add(document.getDocumentLabel());
        }
        GridPane.setConstraints(docsList, 0, 1);

        // Docs navigator container
        docsNavigatorContainer = new GridPane();
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
        file = fileChooser.showOpenDialog(null);

        if (file != null) {

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

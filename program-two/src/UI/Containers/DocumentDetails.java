package UI.Containers;

import UI.Components.Title;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class DocumentDetails {

  private static DocumentDetails instance;
  private static Title documentContentTitle;
  private static Label documentContentViewer;
  private static Tokens tokens;
  private GridPane documentDetailsContainer;

  private DocumentDetails() {
    this.render();
  }

  private void render() {
    // Document content title
    documentContentTitle = new Title();
    GridPane.setConstraints(documentContentTitle.getTitle(), 0, 0);
    GridPane.setMargin(documentContentTitle.getTitle(), new Insets(0, 0, 30, 0));

    // Document content
    documentContentViewer = new Label();
    GridPane.setConstraints(documentContentViewer, 0, 1);

    // Document token title
    Title documentTokensTitle = new Title("Tokens");
    GridPane.setConstraints(documentTokensTitle.getTitle(), 0, 2);
    GridPane.setMargin(documentTokensTitle.getTitle(), new Insets(60, 0, 30, 0));

    // Tokens
    tokens = new Tokens();
    GridPane.setConstraints(tokens.getTokensContainer(), 0, 3);

    // Document details container
    documentDetailsContainer = new GridPane();
    documentDetailsContainer.getChildren()
        .addAll(documentContentTitle.getTitle(),
                documentContentViewer,
                documentTokensTitle.getTitle(),
                tokens.getTokensContainer());
  }

  public static void setDocumentName(String fileName) {
    documentContentTitle.setTitle(fileName);
  }

  public static void setDocumentContent(String fileContent) {
   documentContentViewer.setText(fileContent);
  }

  public static void setDocumentTokes(ArrayList<String> docTokens) {
    tokens.setTokens(docTokens);
  }

  public GridPane getDocumentDetailsContainer() {
    return documentDetailsContainer;
  }

  public static DocumentDetails getInstance() {
    if (instance == null) {
      instance = new DocumentDetails();
    }

    return  instance;
  }
}

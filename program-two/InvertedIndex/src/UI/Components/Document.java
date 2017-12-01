package UI.Components;

import UI.App;
import UI.Containers.DocumentDetails;
import javafx.scene.control.Button;

public class Document {

    private String documentName;
    private String documentData;
    private Button documentLabel;
    private Operations.Document document;

    public Document(String documentName, String documentData) {
        document = new Operations.Document(documentName, documentData);
        this.documentName = documentName;
        this.documentData = documentData;

        this.render();
    }

    private void render() {
        documentLabel = new Button(this.documentName);
        documentLabel.getStyleClass().add("document--name");
        documentLabel.setOnAction(e -> {
            App.showDocumentDetails();
            DocumentDetails.setDocumentContent(this.documentData);
            DocumentDetails.setDocumentName(this.documentName);
            DocumentDetails.setDocumentTokes(document.getTokens());
        });
    }

    public Button getDocumentLabel() {
        return this.documentLabel;
    }

    public String getDocumentData() {
        return this.documentData;
    }
}

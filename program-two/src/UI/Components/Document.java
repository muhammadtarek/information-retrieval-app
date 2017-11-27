package UI.Components;

import javafx.scene.control.Label;

public class Document {

    private String documentName;
    private String documentData;
    private Label documentLabel;

    public Document(String documentName, String documentData) {
        this.documentName = documentName;
        this.documentData = documentData;

        this.render();
    }

    private void render() {
        documentLabel = new Label(this.documentName);
        documentLabel.getStyleClass().add("document--name");
    }

    public Label getDocumentLabel() {
        return this.documentLabel;
    }

    public String getDocumentData() {
        return this.documentData;
    }
}

package com.codecool.dungeoncrawl.display;

import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class SaveDialog {
    private final TextInputDialog textInputDialog;

    public SaveDialog() {
        textInputDialog = new TextInputDialog();
        textInputDialog.getDialogPane().setStyle("-fx-font-family: 'serif'");
        textInputDialog.setHeaderText("Please name your saving:");
        textInputDialog.setContentText("Name:");
    }

    public String getInputName() {
        Optional<String> result = textInputDialog.showAndWait();
        return result.get();
    }

}

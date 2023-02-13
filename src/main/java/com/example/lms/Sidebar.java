package com.example.lms;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Sidebar {

    @FXML
    public void seeArchive(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((JFXButton)actionEvent.getSource()).getParent().getParent().getParent().getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("base-stage.fxml")));
        AnchorPane archiveTable = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("archive.fxml")));

        archiveTable.setLayoutX(stage.getWidth()/2 - archiveTable.getPrefWidth() / 2);
        archiveTable.setLayoutY(stage.getHeight()/2 - archiveTable.getPrefHeight() / 2 + 39);

        ((AnchorPane) root).getChildren().add(archiveTable);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Changed scene");
    }

}

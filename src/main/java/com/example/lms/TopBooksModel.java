package com.example.lms;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class TopBooksModel {

    private final AnchorPane topBooksAnchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("top-books.fxml")));
    protected Parent root;
    public TopBooksModel() throws IOException {
    }

    public AnchorPane getPane() {
        return this.topBooksAnchorPane;
    }

    public void setParent(Parent parent) {
        root = parent;
    }


}

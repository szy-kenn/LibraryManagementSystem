package com.example.lms;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class HomeTopBar {

    @FXML
    private AnchorPane searchBar;

    public void initialize() {

        searchBar.setLayoutX(92);
        searchBar.setLayoutY(-58);

    }

}

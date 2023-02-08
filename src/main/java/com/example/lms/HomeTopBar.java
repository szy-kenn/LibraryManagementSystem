package com.example.lms;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class HomeTopBar {

    @FXML
    private AnchorPane searchBar;

    public void initialize() {

        searchBar.setLayoutX(852);
        searchBar.setLayoutY(-58);

    }

}

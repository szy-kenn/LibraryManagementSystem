package com.example.lms;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class BookWidget {

    @FXML
    private VBox bookHoverButtons;
    @FXML
    private StackPane bookCover;
    @FXML
    private AnchorPane bookWidget;
    public void initialize() {
        bookWidget.setMouseTransparent(true);
        bookCover.setVisible(false);
        bookHoverButtons.setVisible(false);
        bookCover.hoverProperty().addListener((observableValue, newValue, oldValue) -> {
            bookHoverButtons.setVisible(!newValue);
        });

    }

}

package com.example.lms;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Objects;


public class TopBooks {
    @FXML
    private AnchorPane topBooksPane;
    AnchorPane mainPane;


//    private void borrowButtonClicked(ActionEvent event) throws IOException {
//        try {
//            mainPane.getChildren().removeAll(borrowBookPane, newStackPane);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        JFXButton buttonClicked = (JFXButton) event.getSource();
//        AnchorPane buttonParent = (AnchorPane) buttonClicked.getParent();
//        String bookTitle = null;
//        String bookAuthor = null;
//        for (Node node : buttonParent.getChildren()) {
//            if (node.getId() != null) {
//                if (node.getId().equalsIgnoreCase("author")) {
//                    bookAuthor = ((Text) node).getText();
//                } else if (node.getId().equalsIgnoreCase("title")) {
//                    bookTitle = ((Text) node).getText();
//                }
//            }
//        }
//        assert bookTitle != null;
//
//        BorrowBook.setBookTitle(bookTitle);
//        BorrowBook.setBookAuthor(bookAuthor);
//        borrowBookPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("borrow-book.fxml")));
//
//        exitIcon.setX(borrowBookPane.getLayoutX() + borrowBookPane.getPrefWidth() * 1.01);
//        exitIcon.setY(borrowBookPane.getLayoutY() * 1.035);
//        newStackPane = BaseStageController.setHoverProperty(stackPane);
//
//        currentBook = bookTitle;
//        mainPane.getChildren().addAll(borrowBookPane, newStackPane);
//
//    }
}

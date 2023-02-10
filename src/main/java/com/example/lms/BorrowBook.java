package com.example.lms;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BorrowBook {

    public static String bookTitle;
    public static String bookAuthor;

    @FXML
    private AnchorPane borrowBookMainPane;
    @FXML
    private ImageView borrowBookCover;
    @FXML
    private Text borrowBookTitleText;
    @FXML
    private Text borrowBookAuthorText;

    public static void setBookTitle(String newBookTitle) {
        bookTitle = newBookTitle;
    }
    public static void setBookAuthor(String newBookAuthor) {
        bookAuthor = newBookAuthor;
    }
    public void initialize() {
        Stage stage = (Stage) Stage.getWindows().get(0);
        double height = stage.getHeight();
        double width = stage.getWidth();

        borrowBookMainPane.setLayoutX(width / 2 - borrowBookMainPane.getPrefWidth()/2);
        borrowBookMainPane.setLayoutY(height / 2 - borrowBookMainPane.getPrefHeight()/2);

        borrowBookTitleText.setText(bookTitle);
        borrowBookAuthorText.setText(bookAuthor);

        Image image = new Image("assets/book-covers/" + getImageName(bookTitle) + ".jpg");
        borrowBookCover.setImage(image);

    }

    public String getImageName(String bookTitle) {
        StringBuilder newString = new StringBuilder();
        for (Character c : bookTitle.toCharArray()) {
            if (c.toString().isBlank()) {
                newString.append("-");
            } else {
                newString.append(c.toString().toLowerCase());
            }
        }
        return newString.toString();
    }
}

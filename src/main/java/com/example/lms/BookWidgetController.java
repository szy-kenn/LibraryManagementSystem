package com.example.lms;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.util.Objects;

public class BookWidgetController {

    private String currentBook = "";
    StackPane stackPane = new StackPane();
    StackPane newStackPane = new StackPane();
    FontIcon exitIcon = new FontIcon("bx-x");
    Rectangle exitRect = new Rectangle();

    private AnchorPane borrowBookPane;
    private AnchorPane mainPane;
    @FXML
    private JFXButton borrowButton;

    public void initialize() {

        mainPane = (AnchorPane) (borrowButton.getParent()).getParent();
        exitRect.setVisible(false);
        exitRect.setFill(Color.WHITE);
        exitRect.setArcWidth(20);
        exitRect.setArcHeight(20);
        exitRect.setOpacity(0.75);
        exitIcon.setIconSize(24);
        exitIcon.setIconColor(Color.valueOf("#f6f5f1"));
        stackPane.getChildren().addAll(exitRect, exitIcon);

        exitIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mainPane.getChildren().removeAll(borrowBookPane, newStackPane);
                currentBook = "";
            }
        });
    }

    @FXML
    public void onBorrowButtonClicked(ActionEvent actionEvent) throws IOException {
        mainPane = (AnchorPane) (borrowButton.getParent()).getParent().getParent().getParent().getParent().getParent().getParent().getParent();
        ImageView imageView = (ImageView) ((AnchorPane)((JFXButton)actionEvent.getSource()).getParent()).getChildren().get(0);
        String bookUrl = imageView.getImage().getUrl();
        extractBookTitle(bookUrl);
        borrowBookPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("borrow-book.fxml")));
        borrowBookPane.setLayoutX(borrowBookPane.getLayoutX() - 92);
        exitIcon.setX(borrowBookPane.getLayoutX() + borrowBookPane.getPrefWidth() * 1.01);
        exitIcon.setY(borrowBookPane.getLayoutY() * 1.035);
        newStackPane = BaseStageController.setHoverProperty(stackPane);

        mainPane.getChildren().addAll(borrowBookPane, newStackPane);
    }
    public void extractBookTitle(String url) {
        int startIdx = url.indexOf("book-covers") + 12;
        int endIdx = url.indexOf(".jpg");
        String title = url.substring(startIdx, endIdx);
        StringBuilder bookTitle = new StringBuilder();
        for (char letter : title.toCharArray()) {
            if (String.valueOf(letter).equals("-")) {
                bookTitle.append(" ");
            } else {
                bookTitle.append(letter);
            }
        }

        for (Book book: Main.libraryArchive.getAllBooks()) {
            if (book.getBookTitle().equalsIgnoreCase(bookTitle.toString())) {
                BorrowBookController.setBookAuthor(book.getBookAuthor());
                BorrowBookController.setGenre(book.getGenre());
                break;
            }
        }
        currentBook = bookTitle.toString().toUpperCase();
        BorrowBookController.setBookTitle(currentBook);
    }

}
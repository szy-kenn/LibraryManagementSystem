package com.example.lms;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

import static java.lang.Math.abs;

public class BorrowBookController {
    public static String bookTitle;
    public static String bookAuthor;
    public static String genre;
    private LocalDate dateNow = LocalDate.now();
    private LocalDate dateToReturn = LocalDate.ofYearDay(dateNow.getYear(), dateNow.getDayOfYear()+1);

    private AnchorPane borrowBookConfirm;
    private AnchorPane mainPane;
    @FXML
    private AnchorPane borrowBookMainPane;
    @FXML
    private ImageView borrowBookCover;
    @FXML
    private ImageView borrowBookCoverBackground;
    @FXML
    private Rectangle bookTitleBackground;
    @FXML
    private Text borrowBookTitleText;
    @FXML
    private Text borrowBookAuthorText;
    @FXML
    private Text dateBorrowedText;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Text daysBeforeReturn;
    @FXML
    private Text stockNumber;
    @FXML
    private StackPane bookInfoStackPane;
    @FXML
    private VBox bookInfoVBox;
    @FXML
    private JFXButton buttonNextImageSlider;
    @FXML
    private Label errorMessage;

    public static void setBookTitle(String newBookTitle) {
        bookTitle = newBookTitle;
    }
    public static void setBookAuthor(String newBookAuthor) {
        bookAuthor = newBookAuthor;
    }
    public static void setGenre (String newGenre) { genre = newGenre; };
    public void initialize() throws IOException {

        Stage stage = (Stage) Stage.getWindows().get(0);
        double height = stage.getHeight();
        double width = stage.getWidth();

        // for disabling days that are before the date today
        Callback<DatePicker, DateCell> dayCellFactory = newDatePicker -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                int days = (int) ChronoUnit.DAYS.between(dateNow, item);
                if (days <= 0 || days > 21) {
                    setDisable(true);
                    setStyle("-fx-background-color: #c2c8c6;");
                }
            }
        };
        datePicker.setDayCellFactory(dayCellFactory);
        datePicker.getEditor().setDisable(true);
        datePicker.getEditor().setOpacity(1);
        datePicker.getEditor().setStyle("{-fx-focus-color: transparent;}");
        datePicker.setValue(LocalDate.ofYearDay(dateNow.getYear(), dateNow.getDayOfYear()+1));

        borrowBookMainPane.setLayoutX(width / 2 - borrowBookMainPane.getPrefWidth()/2);
        borrowBookMainPane.setLayoutY(height / 2 - borrowBookMainPane.getPrefHeight()/2);

        borrowBookTitleText.setText(bookTitle);
        borrowBookAuthorText.setText(bookAuthor);

        for (Book book: Main.libraryArchive.getAllBooks()) {
            if (book.getBookTitle().equals(bookTitle)) {
                stockNumber.setText(String.valueOf(Main.libraryArchive.getAvailableStock(book)));
            }
        }

        if (bookTitle.length() > 24) {
            bookInfoVBox.setLayoutY(bookInfoVBox.getLayoutY() - 15);
            borrowBookTitleText.setStyle("-fx-font-size: 16px;");
        }
        bookTitleBackground.setWidth(borrowBookTitleText.getLayoutBounds().getWidth() + 10);
        bookTitleBackground.setHeight(borrowBookTitleText.getLayoutBounds().getHeight());
        bookTitleBackground.setLayoutX(borrowBookTitleText.getLayoutX() - 5);
        bookInfoStackPane.setPrefHeight(bookTitleBackground.getHeight());

        Image image = new Image("assets/book-covers/" + getImageName(bookTitle) + ".jpg");
        borrowBookCover.setImage(image);
        borrowBookCoverBackground.setImage(image);
        borrowBookCoverBackground.setOpacity(0.5);
        String dateBorrowed = dateNow.getDayOfWeek() + " " + dateNow.getDayOfMonth() + " " + dateNow.getMonth() + " " + dateNow.getYear();
        dateBorrowedText.setText(dateBorrowed);
    }
    public static String getImageName(String bookTitle) {
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

    public void getDate(ActionEvent actionEvent) {
        dateToReturn = ((DatePicker) actionEvent.getSource()).getValue();
        int daysBefore = (int) ChronoUnit.DAYS.between(dateNow, dateToReturn);
        daysBeforeReturn.setText(daysBefore + " day(s)");
    }

    @FXML
    public void confirmBorrowBook(ActionEvent actionEvent) throws IOException {

        List<BorrowedBook> userBookList = Main.user.getArchive().getBorrowedBooks();
        for (BorrowedBook borrowedBook : userBookList) {
            if (Objects.equals(borrowedBook.getBookTitle(), bookTitle) && !borrowedBook.isReturned()) {
                errorMessage.setText("You already borrowed this book.");
                errorMessage.setVisible(true);
                return;
            }
        }
        for (Book book: Main.libraryArchive.getAllBooks()) {
            if (book.getBookTitle().equals(bookTitle)) {
                if (Main.libraryArchive.getAvailableStock(book) == 0) {
                    errorMessage.setText("Sorry, this book is currently out of stock.");
                    errorMessage.setWrapText(true);
                    errorMessage.setVisible(true);
                    return;
                }
            }
        }
        BorrowedBook book = new BorrowedBook(bookTitle, bookAuthor, genre, dateNow, dateToReturn, false);
        Main.libraryArchive.borrowBook(Main.user, book);
        mainPane = (AnchorPane)(((JFXButton)actionEvent.getSource()).getParent()).getParent().getParent();
        mainPane.getChildren().remove(borrowBookMainPane);

        borrowBookConfirm = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("borrow-book-confirm.fxml")));
        borrowBookConfirm.setLayoutX(mainPane.getPrefWidth() / 2 - borrowBookConfirm.getPrefWidth() / 2);
        borrowBookConfirm.setLayoutY(mainPane.getPrefHeight() / 2 - borrowBookConfirm.getPrefHeight() / 2 + 50);

        Label booksBorrowedCount = (Label) mainPane.lookup("#booksBorrowedCount");
        try {
            booksBorrowedCount.setText(String.valueOf(Main.libraryArchive.getBooksBorrowedCount()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        mainPane.getChildren().add(borrowBookConfirm);
    }

}

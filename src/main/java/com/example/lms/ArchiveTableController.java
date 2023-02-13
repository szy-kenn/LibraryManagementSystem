package com.example.lms;

import com.jfoenix.controls.JFXButton;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class ArchiveTableController {

    @FXML
    private TableView<BorrowedBook> table;

    @FXML
    private TableColumn<BorrowedBook, String> tableColumnAuthor;

    @FXML
    private TableColumn<BorrowedBook, LocalDate> tableColumnDateBorrowed;

    @FXML
    private TableColumn<BorrowedBook, LocalDate> tableColumnDateToReturn;

    @FXML
    private TableColumn<BorrowedBook, String> tableColumnTitle;
    @FXML
    private TableColumn<BorrowedBook, String> tableColumnDateReturned;

    @FXML
    private JFXButton returnBookButton;

    ObservableList<BorrowedBook> list = FXCollections.observableArrayList(
            Main.user.getArchive().getBorrowedBooks()
    );
    public void initialize() {

        returnBookButton.setOpacity(1.0);
        tableColumnTitle.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        tableColumnAuthor.setCellValueFactory(new PropertyValueFactory<>("bookAuthor"));
        tableColumnDateBorrowed.setCellValueFactory(new PropertyValueFactory<>("dateBorrowed"));
        tableColumnDateToReturn.setCellValueFactory(new PropertyValueFactory<>("dateToReturn"));
        tableColumnDateReturned.setCellValueFactory(new PropertyValueFactory<>("dateReturned"));
        table.setItems(list);

        for (TableColumn<?, ?> tc: table.getColumns()) {
            tc.setReorderable(false);
        }

        table.addEventFilter(ScrollEvent.ANY, event -> {
            if (event.getDeltaX() != 0) {
                event.consume();
            }
        });
        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (table.getSelectionModel().getSelectedItems().size() > 0) {
                        BorrowedBook selectedBook = table.getSelectionModel().getSelectedItem();
                        if (selectedBook.isReturned()) {
                            returnBookButton.setDisable(true);
                            returnBookButton.setStyle("-fx-background-color: gray");
                        } else {
                            returnBookButton.setDisable(false);
                            returnBookButton.setStyle("-fx-background-color: #966844;");
                        }
                        returnBookButton.setOpacity(1.0);
                    }
                }
            }
        });
    }

    @FXML
    public void goBack(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("base-stage.fxml")));
        AnchorPane newPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home-view.fxml")));
        ((AnchorPane) root).getChildren().add(newPane);
        Stage stage = (Stage) ((JFXButton)actionEvent.getSource()).getParent().getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Changed scene");
    }
    
    @FXML
    public void returnBook(ActionEvent actionEvent) throws IOException {
        if (!(table.getSelectionModel().getSelectedItems().size() > 0)) {
            System.out.println("No book selected");
            return;
        }
        BorrowedBook borrowedBook = table.getSelectionModel().getSelectedItems().get(0);

        BorrowedBook book = Main.libraryArchive.returnBook(Main.user, borrowedBook);
        table.getItems().set(table.getItems().indexOf(borrowedBook), book);
        returnBookButton.setStyle("-fx-background-color: gray;");
        returnBookButton.setDisable(true);
        System.out.println("you returned the book");
    }
}
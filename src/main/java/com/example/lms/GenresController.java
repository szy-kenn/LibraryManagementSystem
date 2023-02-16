package com.example.lms;

import com.jfoenix.controls.JFXCheckBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GenresController {

    public static final List<String> stringList = new ArrayList<>();
    @FXML
    private AnchorPane genresAnchorPane;
    @FXML
    private Text genreText;
    @FXML
    private VBox VBoxCheckBox;
    @FXML
    private AnchorPane genreTextAnchorPane;
    @FXML
    private VBox vboxBooks;
    @FXML
    private AnchorPane scrollAnchorPane;

    public void initialize() throws IOException {
        stringList.clear();
        genresAnchorPane.setLayoutX(92);

        for (Node node: VBoxCheckBox.getChildren()) {
            if ((node.getId().equals(HomeController.clickedGenre))) {
                ((JFXCheckBox) node).setSelected(true);
                break;
            }
        }

        stringList.add(HomeController.clickedGenre.toUpperCase());
        updateText();
        updateResults();

    }
    @FXML
    public void checkBoxUpdate(ActionEvent actionEvent) throws IOException {
        JFXCheckBox checkBox = (JFXCheckBox) actionEvent.getSource();
        String id = checkBox.getId();
        String genre = id.toUpperCase();

        if (checkBox.isSelected()) {
            stringList.add(genre);
        } else {
            stringList.remove(genre);
        }

        updateText();
        updateResults();
    }

    private void updateText() {
        StringBuilder newGenreText = new StringBuilder();
        for (String str: stringList) {
            newGenreText.append(str);
            if (stringList.indexOf(str) != stringList.size()-1) {
                newGenreText.append(" / ");
            }
        }

        genreText.setText("RESULTS");
    }

    private void updateResults() throws IOException {

        vboxBooks.getChildren().clear();
        for (Book book: Main.libraryArchive.getAllBooks()) {
            if (stringList.contains(book.getGenre())) {
                AnchorPane bookWidget = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("book-widget.fxml")));
                ImageView imageView = (ImageView)bookWidget.lookup("#bookCover");
                Image image = new Image("assets/book-covers/" + BorrowBookController.getImageName(book.getBookTitle()) + ".jpg");
                imageView.setImage(image);
                if (vboxBooks.getChildren().size() == 0 ||
                        ((HBox)vboxBooks.getChildren().get(vboxBooks.getChildren().size()-1)).getChildren().size() == 5) {
                    HBox newHBox = new HBox();
                    newHBox.setSpacing(20);
                    newHBox.getChildren().add(bookWidget);
                    vboxBooks.getChildren().add(newHBox);
                } else {
                    for (Node box : vboxBooks.getChildren()) {
                        if (((HBox) box).getChildren().size() < 5) {
                            ((HBox) box).getChildren().add(bookWidget);
                        }
                    }
                }
            }
         }
        scrollAnchorPane.setPrefWidth(vboxBooks.getMaxWidth());
    }

}

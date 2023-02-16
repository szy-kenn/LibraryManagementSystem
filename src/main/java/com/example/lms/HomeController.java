package com.example.lms;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class HomeController {
    public static String clickedGenre = "";
    private String currentBook = "";
    private Parent root;
    private AnchorPane borrowBookPane;
    StackPane stackPane = new StackPane();
    StackPane newStackPane = new StackPane();
    FontIcon exitIcon = new FontIcon("bx-x");
    Rectangle exitRect = new Rectangle();
    JFXHamburger hamburger = new JFXHamburger();
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Text welcomeMessage;
    @FXML
    private Label totalMembers;
    @FXML
    private Label booksBorrowedCount;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private ImageView imageSlider1;
    @FXML
    private ImageView imageSlider2;
    @FXML
    private ImageView imageSlider3;
    @FXML
    private ImageView imageSlider4;
    @FXML
    private ImageView imageSlider5;
    @FXML
    private ImageView imageSlider6;
    @FXML
    private AnchorPane sliderPane;
    @FXML
    private ImageView kidsSection;
    @FXML
    private ImageView romanceSection;
    @FXML
    private ImageView fantasySection;
    @FXML
    private ImageView mysterySection;
    @FXML
    private ImageView historySection;

    public void initialize() throws IOException {
        mainPane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("scrollbar.css")).toExternalForm());
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("base-stage.fxml")));
        AnchorPane homeTopPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home-topbar.fxml")));
        Stage stage = (Stage) Stage.getWindows().get(0);
        double height = stage.getHeight();
        double width = stage.getWidth();
//        System.out.printf("%f %f", height, width);
        String welcomeText = "Welcome, " + Main.user.getUsername() + "!";
        welcomeMessage.setText(welcomeText);
        totalMembers.setText(String.valueOf(Main.libraryArchive.getTotalMemberCount()));
        booksBorrowedCount.setText(String.valueOf(Main.libraryArchive.getBooksBorrowedCount()));
        VBox vbox = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sidebar.fxml")));
        System.out.println(drawer.getWidth());
        drawer.setSidePane(vbox);
//        drawer.setOpacity(0.8);

        playSlider();

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

        mainPane.setLayoutX(width / 2 - (mainPane.getPrefWidth() / 2));
        mainPane.setLayoutY(height * 0.55 - (mainPane.getPrefHeight() / 2));

        hamburger.setId("hamburger");
        hamburger.setLayoutX(30);
        hamburger.setLayoutY(-45);
        hamburger.setMaxSize(32, 32);
        hamburger.getStylesheets().add(Objects.requireNonNull(getClass().getResource("hamburger.css")).toExternalForm());

        HamburgerSlideCloseTransition transition = new HamburgerSlideCloseTransition(hamburger);
        transition.setRate(-1); // normal or default transition

        hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            if (drawer.isOpened() && !(drawer.isClosing())) {
                System.out.println("closed");
                drawer.close();
                drawer.setMouseTransparent(true);
                transition.setRate(transition.getRate() * -1);
                transition.play();
            } else if (drawer.isClosed() && !(drawer.isOpening())) {
                System.out.println("opened");
                drawer.open();
                drawer.setMouseTransparent(false);
                transition.setRate(transition.getRate() * -1);
                transition.play();
            }
        });

        mainPane.getChildren().addAll(hamburger, homeTopPane);
        ((AnchorPane) root).getChildren().add(mainPane);
    }

    @FXML
    private void borrowButtonClicked(ActionEvent event) throws IOException {
        try {
            mainPane.getChildren().removeAll(borrowBookPane, newStackPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFXButton buttonClicked = (JFXButton) event.getSource();
        AnchorPane buttonParent = (AnchorPane) buttonClicked.getParent();
        String bookTitle = "";
        String bookAuthor = "";
        String genre = "";
        for (Node node : buttonParent.getChildren()) {
            if (node.getId() != null) {
                if (node.getId().equalsIgnoreCase("aboutbook")) {
                    for (Node childNode : ((VBox) node).getChildren()) {
                        if (childNode.getId().equalsIgnoreCase("author")) {
                            bookAuthor = ((Text) childNode).getText();
                        } else if (childNode.getId().equalsIgnoreCase("title")) {
                            bookTitle = ((Text) childNode).getText();
                        } else if(childNode.getId().equalsIgnoreCase("genre")) {
                            genre = ((Text)childNode).getText();
                        }
                    }
                }
            }
        }
        assert bookTitle != null;

        BorrowBookController.setBookTitle(bookTitle);
        BorrowBookController.setBookAuthor(bookAuthor);
        BorrowBookController.setGenre(genre);
        borrowBookPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("borrow-book.fxml")));
        exitIcon.setX(borrowBookPane.getLayoutX() + borrowBookPane.getPrefWidth() * 1.01);
        exitIcon.setY(borrowBookPane.getLayoutY() * 1.035);
        newStackPane = BaseStageController.setHoverProperty(stackPane);

        currentBook = bookTitle;
        mainPane.getChildren().addAll(borrowBookPane, newStackPane);

    }

    private void playSlider() {
        new Thread() {
            public void run() {

                int imageCounter = 0;
                boolean reset = false;
                FadeTransition imgTransition1 = new FadeTransition(Duration.seconds(0.5), imageSlider1);
                FadeTransition imgTransition2 = new FadeTransition(Duration.seconds(0.5), imageSlider2);
                FadeTransition imgTransition3 = new FadeTransition(Duration.seconds(0.5), imageSlider3);
                FadeTransition imgTransition4 = new FadeTransition(Duration.seconds(0.5), imageSlider4);
                FadeTransition imgTransition5 = new FadeTransition(Duration.seconds(0.5), imageSlider5);
                FadeTransition imgTransition6 = new FadeTransition(Duration.seconds(0.5), imageSlider6);
                List<FadeTransition> fadeTransitionList = Arrays.asList(imgTransition6, imgTransition5, imgTransition4, imgTransition3, imgTransition2, imgTransition1);
                for (FadeTransition ft : fadeTransitionList) {
                    ft.setInterpolator(Interpolator.EASE_BOTH);
                }
                while (true) {
                    try {
                        Thread.sleep(7000);
                        FadeTransition currentTransition = fadeTransitionList.get(imageCounter);
                        if (!reset) {
                            currentTransition.setToValue(0.0);
                        } else {
                            currentTransition.setToValue(1.0);
                            currentTransition.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    for (FadeTransition ft : fadeTransitionList) {
                                        if (!(ft.equals(currentTransition))) {
                                            ft.getNode().setOpacity(1.0);
                                        }
                                    }
                                }
                            });
                        }
                        currentTransition.play();
                        if (imageCounter == fadeTransitionList.size() - 2) {
                            imageCounter = 0;
                            reset = true;
                        } else {
                            if (!reset) {
                                imageCounter++;
                            } else {
                                reset = false;
                            }
                        }

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        }.start();
    }

    @FXML
    public void onGenreClick(MouseEvent mouseEvent) throws IOException {

        ImageView imageView = (ImageView) mouseEvent.getSource();
        System.out.println(imageView.getId());

        List<Node> mainPaneChildren = new ArrayList<>(mainPane.getChildren().stream().toList());

        for (Node node: mainPaneChildren) {
            if (node.getId() != null) {
                if (!node.getId().equals("drawer") && !node.getId().equals("sidebarAnchorPane")
                        && !node.getId().equals("hamburger") &&!node.getId().equals("searchBar")) {
                    mainPane.getChildren().remove(node);
                }
            } else {
                mainPane.getChildren().remove(node);
            }
        }
        clickedGenre = imageView.getId();
        AnchorPane genresAnchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("genres.fxml")));

        mainPane.getChildren().add(0, genresAnchorPane);
    }

}

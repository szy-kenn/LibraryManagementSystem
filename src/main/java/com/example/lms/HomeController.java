package com.example.lms;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Objects;

public class HomeController {
    @FXML
    private AnchorPane mainPane;
    @FXML
    private JFXDrawer drawer;

    private JFXHamburger hamburger = new JFXHamburger();


    public void initialize() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("base-stage.fxml")));
        AnchorPane homeTopPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home-topbar.fxml")));

        Stage stage = (Stage) Stage.getWindows().get(0);
        double height = stage.getHeight();
        double width = stage.getWidth();
//        System.out.printf("%f %f", height, width);
        VBox vbox = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sidebar.fxml")));
        System.out.println(drawer.getWidth());
        drawer.setSidePane(vbox);
        drawer.setOpacity(0.8);

        mainPane.setLayoutX(width / 2 - (mainPane.getPrefWidth() / 2));
        mainPane.setLayoutY(height * 0.55 - (mainPane.getPrefHeight() / 2));
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
                    transition.setRate(transition.getRate()*-1);
                    transition.play();
                } else if(drawer.isClosed() && !(drawer.isOpening())) {
                    System.out.println("opened");
                    drawer.open();
                    transition.setRate(transition.getRate()*-1);
                    transition.play();
                }
        });

        mainPane.getChildren().addAll(hamburger, homeTopPane);
        ((AnchorPane) root).getChildren().add(mainPane);
    }

}

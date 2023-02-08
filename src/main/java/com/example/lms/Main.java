package com.example.lms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    private final Image icon = new Image("C:\\Users\\PC\\Documents\\JAVA-TEST\\LMS\\src\\main\\resources\\assets\\icon.png");
    private final String cssStyling = Objects.requireNonNull(getClass().getResource("main.css")).toExternalForm();

    public void initialize(Stage stage) {
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.setTitle("Library Management System");
        stage.initStyle(StageStyle.UNDECORATED);
    }

    @Override
    public void start(Stage stage) throws IOException {
        initialize(stage);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("base-stage.fxml")));
        AnchorPane mainPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-view.fxml")));
        ((AnchorPane) root).getChildren().add(mainPane);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(cssStyling);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
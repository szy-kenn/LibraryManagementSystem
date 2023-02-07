package com.example.lms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {

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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(cssStyling);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
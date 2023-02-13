package com.example.lms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.util.Objects;

public class Main extends Application {

    private final Image icon = new Image("C:\\Users\\PC\\Documents\\GitHub\\LibraryManagementSystem-CaseStudy\\src\\main\\resources\\assets\\logo.png");
    private final String cssStyling = Objects.requireNonNull(getClass().getResource("main.css")).toExternalForm();

    public static User user;
    public static LibraryArchive libraryArchive;
    public void initialize(Stage stage) {
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.setTitle("Library Management System");
        stage.initStyle(StageStyle.UNDECORATED);
    }

    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        File f = new File("src/main/java/com/example/lms/userdata/_LIBRARY_ARCHIVE_.txt");
        if (f.exists() && !f.isDirectory()) {
            System.out.println("Loading LibraryArchive File...");
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/main/java/com/example/lms/userdata/_LIBRARY_ARCHIVE_.txt"));
            libraryArchive = (LibraryArchive) in.readObject();
        } else {
            System.out.println("Creating new LibraryArchive file...");
            libraryArchive = new LibraryArchive();
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/main/java/com/example/lms/userdata/_LIBRARY_ARCHIVE_.txt"));
            out.writeObject(libraryArchive);
        }
        initialize(stage);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("base-stage.fxml")));
        AnchorPane mainPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-view.fxml")));
        ((AnchorPane) root).getChildren().add(mainPane);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(cssStyling);
        stage.setScene(scene);
        stage.show();
    }
    public static void setUser(String username, String password) throws IOException {
        user = new User(username, password);
        libraryArchive.addUser(user);
    }
    public static void loadUser(String username){
        for (User libUser: libraryArchive.getUsersList()) {
            if (libUser.getUsername().equals(username)) {
                user = libUser;
                return;
            }
        }
    }
    public static void main(String[] args) {
        launch();
    }
}
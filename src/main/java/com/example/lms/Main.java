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

/**
 * This class holds the psvm (public static void main) method that will be executed first
 * when the application is launched
 */

public class Main extends Application {

    // instantiate the needed objects for the icon, design styles, and the static objects "user" and "libraryArchive"
    // that can be accessed by every class of this project
    String filepath = new File("").getAbsolutePath();
//    private final Image icon = new Image(filepath + "/src/main/resources/assets/logo.png");
    private final String cssStyling = Objects.requireNonNull(getClass().getResource("main.css")).toExternalForm();

    public static User user;
    public static LibraryArchive libraryArchive;
    public void initialize(Stage stage) {
        /*
        Initialize the stage:
        - sets the icon
        - makes the window not resizable
        - setting the title for the program
        - makes the stage undecorated, meaning the upper bar that holds
            maximize, minimum, and close button will be removed
         */
//        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.setTitle("Library Management System");
        stage.initStyle(StageStyle.UNDECORATED);
    }

    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {

        /*
        This block of code will just check if _LIBRARY_ARCHIVE_.txt exists,
        if not, then create one.

        This specific file will hold ALL THE DATA that this program will produce, including
        the users lists, the available books, user's archive, borrowed books, etc.
         */
        File f = new File( "userdata/_LIBRARY_ARCHIVE_.txt");
        if (f.exists() && !f.isDirectory()) {
            System.out.println("Loading LibraryArchive File...");
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream("userdata/_LIBRARY_ARCHIVE_.txt")
            );
            libraryArchive = (LibraryArchive) in.readObject();
        } else {
            System.out.println(filepath);
            System.out.println("Creating new LibraryArchive file...");
            libraryArchive = new LibraryArchive();
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream( "userdata/_LIBRARY_ARCHIVE_.txt"))
                    ;
            out.writeObject(libraryArchive);
        }

        initialize(stage);
        // load the fxml file to be used as the base for the stage, and the main view (Login Pane)
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("base-stage.fxml")));
        AnchorPane mainPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-view.fxml")));
        ((AnchorPane) root).getChildren().add(mainPane);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(cssStyling);
        stage.setScene(scene);
        stage.show();
    }
    public static void setUser(String username, String password) throws IOException {
        /*
        this method is only called when creating a new username (in the MainController class)
         */
        user = new User(username, password);
        libraryArchive.addUser(user);
    }
    public static void loadUser(String username){
        /*
        only called when logging in (called by the MainController Class)
         */
        for (User libUser: libraryArchive.getUsersList()) {
            if (libUser.getUsername().equals(username)) {
                user = libUser;
                return;
            }
        }
    }
    public static void main(String[] args) {

        // launches the Application
        launch();
    }
}
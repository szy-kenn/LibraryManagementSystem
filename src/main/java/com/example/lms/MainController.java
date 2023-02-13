package com.example.lms;

import static com.example.lms.Constants.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.util.Objects;


public class MainController {
    @FXML private AnchorPane mainPane;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorMessage;
    public LoginButton loginButton = new LoginButton();

    public MainController() throws IOException {
    }

    public void initialize() throws IOException {
        mainPane.setLayoutX(314);
        mainPane.setLayoutY(220);

        usernameField.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    if (!usernameField.getCharacters().isEmpty()) {
                        passwordField.requestFocus();
                    } else {
                        errorMessage.setText("Please enter your username.");
                        errorMessage.setVisible(true);
                    }
                }
            }
        });
        passwordField.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    if (!passwordField.getCharacters().isEmpty()) {
                        loginButton.requestFocus();
                        try {
                            getUsernamePassword();
                        } catch (IOException | ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        errorMessage.setText("Please enter your password.");
                        errorMessage.setVisible(true);
                    }
                }
            }
        });
        loginButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    getUsernamePassword();
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        mainPane.getChildren().addAll(loginButton);
    }

    private void enterKeyLogin() {

    }
    private void getUsernamePassword() throws IOException, ClassNotFoundException {
        String username = usernameField.getCharacters().toString();
        String password = passwordField.getCharacters().toString();
        System.out.println("USERNAME: " + username);
        System.out.println("PASSWORD: " + password);

        if (username.isBlank() || password.isBlank()) {
            errorMessage.setText("Please enter your username and password.");
            errorMessage.setVisible(true);
            return;
        }

        // check if username and password is valid
        for (User user: Main.libraryArchive.getUsersList()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                System.out.println("Loading user...");
                Main.loadUser(username);
                changeScene();
                return;
            }
        }
        System.out.println("Creating new user...");
        Main.setUser(username, password);
        changeScene();
    }

    private void changeScene() throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("base-stage.fxml")));
        AnchorPane newPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home-view.fxml")));
        ((AnchorPane) root).getChildren().add(newPane);
        Stage stage = (Stage) usernameField.getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Changed scene");
    }

}
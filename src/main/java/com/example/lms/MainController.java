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
    @FXML public AnchorPane mainPane;
    @FXML
    public TextField usernameField;
    @FXML
    public PasswordField passwordField;
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
                        System.out.println("No characters");
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
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        System.out.println("No characters");
                    }
                }
            }
        });
        loginButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    getUsernamePassword();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        mainPane.getChildren().addAll(loginButton);
    }

    private void enterKeyLogin() {

    }
    private void getUsernamePassword() throws IOException {
        String username = usernameField.getCharacters().toString();
        String password = passwordField.getCharacters().toString();
        System.out.println("USERNAME: " + username);
        System.out.println("PASSWORD: " + password);

        // check if username and password is valid
        if (username.equalsIgnoreCase("ken") && password.equalsIgnoreCase("ken")) {
            changeScene();
        } else {
            System.out.println("wrong");
        }

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
package com.example.lms;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

/**
 * This class is used only as a controller for the Main View / Starting View of the Application when the
 * user is prompted to input their login credentials or create a new account for themselves
 * - used by the "main-view.fxml" in the resource folder that holds the design for the view
 */

public class MainController {

    // all of these variables are declared with the reference to the objects inside its corresponding FXML file
    @FXML private AnchorPane mainPane;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorMessage;
    @FXML
    private Text createAccount;
    @FXML
    private Text logIntoText;
    @FXML
    private Text logIntoText1;
    public LoginButton loginButton = new LoginButton();

    // boolean value to store if the user is in the Login or Signup screen
    private boolean switched = false;

    public MainController() throws IOException {
    }

    public void initialize() throws IOException {

        // set the main pane (Login Pane) in the center
        mainPane.setLayoutX(314);
        mainPane.setLayoutY(220);

        // set the method to be called when the ENTER KEY is pressed in the username field
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
        // set the method to be called when the ENTER KEY is pressed in the password field
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

        // set the method to be called when the button is CLICKED
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

        // changes the color of the text when hovered
        createAccount.hoverProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean newVal, Boolean oldVal) {
                if (newVal) {
                    createAccount.setFill(Color.WHITE);
                } else {
                    createAccount.setFill(Color.valueOf("#d1413f"));
                }
            }
        });

        // add the login button to the Login Pane
        mainPane.getChildren().addAll(loginButton);
    }

    @FXML
    private void createAnAccountClicked(MouseEvent mouseEvent) {

        // clear the text fields if changing scenes
        usernameField.clear();
        passwordField.clear();
        errorMessage.setVisible(false);

        switched = !switched;

        if (switched) {
            // if "Create an Account" is clicked, change the content of the texts
            logIntoText.setText("CREATE A");
            logIntoText1.setText("NEW ACCOUNT");
            createAccount.setText("or Log Into Your Account now!");
            loginButton.setLoginText("SIGNUP");
        } else {
            // if "Log into your Account" is clicked
            logIntoText.setText("LOG INTO");
            logIntoText1.setText("YOUR ACCOUNT");
            createAccount.setText("or Create an Account now!");
            loginButton.setLoginText("LOGIN");
        }
    }

    private void getUsernamePassword() throws IOException, ClassNotFoundException {

        /*
         * this method is only called when the button is clicked, or the ENTER KEY is pressed
         * on the password field
         * - checks the login credentials passed to the fields if it's valid or not
         */

        String username = usernameField.getCharacters().toString();
        String password = passwordField.getCharacters().toString();
        System.out.println("USERNAME: " + username);
        System.out.println("PASSWORD: " + password);

        // return if either two fields is blank
        if (username.isBlank() || password.isBlank()) {
            errorMessage.setText("Please enter your username and password.");
            errorMessage.setVisible(true);
            return;        }

        // check if username is valid if creating an account
        if (switched) {
            if (username.length() > 12) {
                errorMessage.setText("Username is too long.");
                errorMessage.setVisible(true);
                return;
            } else if (username.length() < 3) {
                errorMessage.setText("Username is too short.");
                errorMessage.setVisible(true);
                return;
            } else if (username.contains(" ")) {
                errorMessage.setText("Spaces are not allowed.");
                errorMessage.setVisible(true);
                return;
            } else {
                for (User user: Main.libraryArchive.getUsersList()) {
                    if (user.getUsername().equals(username)) {
                        errorMessage.setText("Username is already taken.");
                        errorMessage.setVisible(true);
                        return;
                    }
                }
            }

            // check if the password is too weak
            if (password.length() < 6) {
                errorMessage.setText("Password is too short.");
                errorMessage.setVisible(true);
                return;
            } else {
                int uppercase = 0;
                int symbol = 0;
                int digit = 0;

                for (char letter : password.toCharArray()) {
                    if (Character.isUpperCase(letter)) {
                        uppercase++;
                    } else if (Character.isDigit(letter)) {
                        digit++;
                    } else {
                        if (String.valueOf(letter).isBlank()) {
                            errorMessage.setText("Spaces are not allowed.");
                            errorMessage.setVisible(true);
                            return;
                        } else {
                            symbol++;
                        }
                    }
                }

                if (digit == 0 || symbol == 0 || uppercase == 0) {
                    errorMessage.setText("Password must contain at least 1 digit, symbol, and uppercase letter");
                    errorMessage.setVisible(true);
                    return;
                }
                Main.setUser(username, password);
                changeScene();
                return;
            }

        }
        // if logging in
        // check if username and password is valid
        boolean usernameFound = false;
        for (User user: Main.libraryArchive.getUsersList()) {

            if (user.getUsername().equals(username)) {
                usernameFound = true;
                if (user.getPassword().equals(password)) {
                    Main.loadUser(username);
                    changeScene();
                    return;
                }
            }
        }
        if (!usernameFound) {
            errorMessage.setText("Account does not exist.");
            errorMessage.setVisible(true);
            return;
        }
        errorMessage.setText("Wrong username or password.");
        errorMessage.setVisible(true);
        return;
    }

    private void changeScene() throws IOException {
        // changes scene when the login check / signup is verified

        /*
        loads a new scene (home-view) and pass it to the current stage to present a new scene to the user
         */
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("base-stage.fxml")));
        AnchorPane newPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home-view.fxml")));
        ((AnchorPane) root).getChildren().add(newPane);
        Stage stage = (Stage) usernameField.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
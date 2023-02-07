package com.example.lms;

import static com.example.lms.Constants.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;


public class HelloController {
    @FXML
    public AnchorPane mainPane;
    public StackPane exitIconStack = new StackPane();
    public StackPane minimizeIconStack = new StackPane();
    public void initialize() {
        FontIcon exitIcon = new FontIcon("bx-x");
        FontIcon minimizeIcon = new FontIcon("bx-minus");
        Rectangle exitRect = new Rectangle();
        Rectangle minimizeRect = new Rectangle();
        DropShadow dropShadow = new DropShadow();

        dropShadow.setColor(Color.valueOf(GRAYISH_WHITE));

        exitRect.setVisible(false);
        exitRect.setFill(Color.WHITE);
        exitRect.setArcWidth(20);
        exitRect.setArcHeight(20);
        exitRect.setOpacity(0.75);
        exitRect.setEffect(dropShadow);

        minimizeRect.setVisible(false);
        minimizeRect.setFill(Color.WHITE);
        minimizeRect.setArcWidth(20);
        minimizeRect.setArcHeight(20);
        minimizeRect.setOpacity(0.75);
        minimizeRect.setEffect(dropShadow);

        minimizeIcon.setIconSize(24);
        minimizeIcon.setX(1190);
        minimizeIcon.setY(20);
        minimizeIcon.setIconColor(Color.valueOf("#f6f5f1"));

        exitIcon.setIconSize(24);
        exitIcon.setX(1230);
        exitIcon.setY(20);
        exitIcon.setIconColor(Color.valueOf("#f6f5f1"));

        exitIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Platform.exit();
            }
        });
        minimizeIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Stage stage = (Stage)mainPane.getScene().getWindow();
                stage.setIconified(true);
            }
        });

        exitIconStack.getChildren().addAll(exitRect, exitIcon);
        minimizeIconStack.getChildren().addAll(minimizeRect, minimizeIcon);

        mainPane.getChildren().addAll(exitIconStack, minimizeIconStack);

        setHoverProperty(exitIconStack);
        setHoverProperty(minimizeIconStack);
    }

    private void setHoverProperty(StackPane stackPane) {
        Rectangle rect = (Rectangle) stackPane.getChildren().get(0);
        FontIcon icon = (FontIcon) stackPane.getChildren().get(1);
        int size = icon.getIconSize();

        stackPane.setLayoutX(icon.getX());
        stackPane.setLayoutY(icon.getY());

        rect.setX(icon.getX());
        rect.setY(icon.getY());
        rect.setWidth(32);
        rect.setHeight(32);

        icon.hoverProperty().addListener((ChangeListener<Boolean>)(observable, oldValue, newValue) -> {
            if (newValue) {
                rect.setVisible(true);
                icon.setIconColor(Paint.valueOf(DARK_BLUE_GRAY));
            } else {
                rect.setVisible(false);
                icon.setIconColor(Paint.valueOf(GRAYISH_WHITE));
            }
        });

    }
    @FXML
    private Button loginButton;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    public void onLoginClick(ActionEvent event) {
        System.out.println("LOGIN BUTTON CLICKED");
        System.out.println("USERNAME: " + usernameField.getCharacters());
        System.out.println("PASSWORD: " + passwordField.getCharacters());
    }
}
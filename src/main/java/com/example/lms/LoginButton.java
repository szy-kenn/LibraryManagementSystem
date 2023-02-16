package com.example.lms;

import com.example.lms.Constants.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBoundsType;

public class LoginButton extends StackPane{

    Rectangle rectangle;
    Text loginText;
    public LoginButton() {

        rectangle = new Rectangle();
        loginText = new Text("LOGIN");

        DropShadow dsEffect = new DropShadow();
        dsEffect.setOffsetY(10);
        dsEffect.setColor(Color.color(0, 0, 0, 0.5));
        dsEffect.setBlurType(BlurType.GAUSSIAN);
        dsEffect.setRadius(10);

        Stop[] stops = new Stop[] {new Stop(0, Color.valueOf("#d1413f")), new Stop(1, Color.valueOf("#ae3735"))};
        LinearGradient lg = new LinearGradient(0, 0, 1, 0, true, CycleMethod.REFLECT, stops);

        this.setLayoutX(437);
        this.setLayoutY(292);
        this.setMaxSize(105, 30);

        loginText.setMouseTransparent(true);
        loginText.setFill(Color.WHITE);
        loginText.setBoundsType(TextBoundsType.LOGICAL_VERTICAL_CENTER);
        loginText.setFont(new Font("JetBrains Mono ExtraBold", 15));

        rectangle.setWidth(105);
        rectangle.setHeight(30);
        rectangle.setArcWidth(30);
        rectangle.setArcHeight(30);
        rectangle.setEffect(dsEffect);
        rectangle.setFill(lg);

        rectangle.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                rectangle.setFill(Paint.valueOf("#a03332"));
                Stop[] stops = new Stop[] {new Stop(0, Color.valueOf("#ae3735")), new Stop(1, Color.valueOf("#7f2927"))};
                LinearGradient lg = new LinearGradient(0, 0, 1, 0, true, CycleMethod.REFLECT, stops);
                rectangle.setFill(lg);
            }
        });
        rectangle.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Stop[] stops = new Stop[] {new Stop(0, Color.valueOf("#d1413f")), new Stop(1, Color.valueOf("#ae3735"))};
                LinearGradient lg = new LinearGradient(0, 0, 1, 0, true, CycleMethod.REFLECT, stops);
                rectangle.setFill(lg);
            }
        });
        rectangle.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Stop[] stops = new Stop[] {new Stop(0, Color.valueOf("#7f2927")), new Stop(1, Color.valueOf("#652120"))};
                LinearGradient lg = new LinearGradient(0, 0, 1, 0, true, CycleMethod.REFLECT, stops);
                rectangle.setFill(lg);
//                System.out.println("USERNAME: " + usernameField.getCharacters());
//                System.out.println("PASSWORD: " + passwordField.getCharacters());
            }
        });
        rectangle.setOnMouseReleased(rectangle.onMouseEnteredProperty().get());
        rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("LOGIN BUTTON CLICKED");
            }
        });
        this.getChildren().addAll(rectangle, loginText );
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public Text getLoginText() {
        return loginText;
    }

    public void setLoginText(String loginText) {
        this.loginText.setText(loginText);
    }
}

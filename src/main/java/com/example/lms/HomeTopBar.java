package com.example.lms;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;

public class HomeTopBar {

    @FXML
    private AnchorPane searchBar;
    @FXML
    private Label timeText;

    public void initialize() {

        searchBar.setLayoutX(92);
        searchBar.setLayoutY(-58);
        ZonedDateTime time = ZonedDateTime.now();
        String formattedTime = String.format("|     %s %02d %s %02d:%02d:%02d", time.getMonth(), time.getDayOfMonth(), time.getYear(), time.getHour(), time.getMinute(), time.getSecond());
        timeText.setText(formattedTime);
        new Thread() {

            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        ZonedDateTime time = ZonedDateTime.now();
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                String formattedTime = String.format("|     %s %02d %s %02d:%02d:%02d", time.getMonth(), time.getDayOfMonth(), time.getYear(), time.getHour(), time.getMinute(), time.getSecond());
                                timeText.setText(formattedTime);
                            }
                        });

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }.start();

    }

}

package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReviewController implements Initializable  {
    @FXML
    AnchorPane anchorPane;
    static Button button = new Button("Hiii");
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(1);
        button.setLayoutX(100);
        button.setLayoutY(100);
        button.setPrefHeight(100);
        button.setPrefWidth(100);
        anchorPane.getChildren().add(button);
    }
}

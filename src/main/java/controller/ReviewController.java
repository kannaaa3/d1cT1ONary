package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ReviewController implements Initializable  {
    @FXML
    AnchorPane anchorPane;
    static int numberofWordlist = 15;
    List<Button> buttons = new ArrayList<Button>();
    int h = 0;
    int w = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < numberofWordlist; i++) {
            Button button = new Button("MyWordlist");
            button.setPrefWidth(200);
            button.setPrefHeight(200);
            button.setLayoutX(10 + (w % 3) * 220 );
            w++;
            button.setLayoutY(10 + h * 220);
            if (w % 3 == 0) h++;
            anchorPane.getChildren().add(button);
            buttons.add(button);
        }

    }
}

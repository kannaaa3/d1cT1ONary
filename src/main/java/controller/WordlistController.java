package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static controller.MainController.i;
import static controller.MainController.user;

public class WordlistController implements Initializable {
    @FXML
    private AnchorPane wordlistView;
    @FXML
    public Label nameofWordlist;
    static int numberofWord = 15;
    static List<String> wordsofWordlist = new ArrayList<String>();
    List<Label> labels = new ArrayList<Label>();
    int h = 0;
    int w = 0;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //nameofWordlist.setText(user.getAllWordLists().get(i).getName());
        for (int i = 0; i < numberofWord; i++) {
            Label label = new Label();
            label.setText("newwwww");
            label.setBackground(new Background(
                    new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            label.setPrefWidth(220);
            label.setPrefHeight(35);
            label.setLayoutX(10 + (w % 2) * 240);
            w++;
            label.setLayoutY(10 + h * 45);
            if (w % 2 == 0) h++;
            wordlistView.getChildren().add(label);
        }
    }
}

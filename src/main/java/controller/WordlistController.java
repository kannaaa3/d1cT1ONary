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
import model.word.Word;
import model.word.WordList;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static controller.MainController.*;

public class WordlistController implements Initializable {
    @FXML
    private AnchorPane wordlistView;
    @FXML
    public Label nameofWordlist;
    static int numberOfWord;
    static List<String> wordsofWordlist = new ArrayList<String>();
    List<Label> labels = new ArrayList<Label>();
    static int h = 0;
    static int w = 0;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //if (user.getAllWordLists().size() > 0) numberOfWord = user.getAllWordLists().get(k).getWords().size();
    }

    public void displayWordList(WordList wordList) {
        w = 0;
        h = 0;
        for (int i = 0; i < wordList.size(); i++) {
            Label label = new Label();
            label.setText(wordList.getWord(i).getWord());
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

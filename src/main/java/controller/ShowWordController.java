package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;
import model.word.Word;

public class ShowWordController implements Initializable {
    @FXML
    public Label word;
    @FXML
    public Label phoneticText;
    @FXML
    public Label partOfSpeech;
    @FXML
    public Label definition;
    @FXML
    public Label example;
    @FXML
    public Label synonyms;
    @FXML
    public Label antonyms;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void displayWord(Word myWord) {
        String[] x = myWord.getMeaning().getSynonyms();
        String s1 = "";
        for (int i = 0; i < x.length; i++) s1 = s1 + x[i] + "\n";
        String[] y = myWord.getMeaning().getAntonyms();
        String s2 = "";
        for (int j = 0; j < y.length; j++) s2 = s2 + y[j] + "\n";
        word.setText(myWord.getWord());
        phoneticText.setText(myWord.getPhonetic().getText());
        partOfSpeech.setText(myWord.getMeaning().getPartOfSpeech());
        definition.setText(myWord.getMeaning().getDefinition());
        example.setText(myWord.getMeaning().getExample());
        synonyms.setText(s1);
        antonyms.setText(s2);
    }
}

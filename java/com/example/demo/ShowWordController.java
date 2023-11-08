package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.demo.ScreenController.currentWord;

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
        String[] x = myWord.meaning.getSynonyms();
        String s1 = "";
        for (int i = 0; i < x.length; i++) s1 = s1 + x[i] + "\n";
        String[] y = myWord.meaning.getAntonyms();
        String s2 = "";
        for (int j = 0; j < y.length; j++) s2 = s2 + y[j] + "\n";
        word.setText(myWord.word);
        phoneticText.setText(myWord.phonetic.getText());
        partOfSpeech.setText(myWord.meaning.getPartOfSpeech());
        definition.setText(myWord.meaning.getDefinition());
        example.setText(myWord.meaning.getExample());
        synonyms.setText(s1);
        antonyms.setText(s2);
    }
}

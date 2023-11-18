package controller;

import javafx.beans.value.ObservableValue;
import javafx.collections.SetChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.layout.AnchorPane;
import model.dictionary.Dictionary;
import model.word.Meaning;
import model.word.Phonetic;
import model.word.Word;

import static controller.SlideMenuController.*;


public class ShowWordController implements Initializable {
    @FXML
    public AnchorPane showWordScreen;
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
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }



    @FXML
    public void displayWord() {
        myWord = dictionary.getWordData(showWord);
        System.out.println("Vao ham");
        System.out.println(showWord);
        String[] x = myWord.getMeaning().synonyms();
        String s1 = "";
        for (int i = 0; i < x.length; i++) s1 = s1 + x[i] + "\n";
        String[] y = myWord.getMeaning().antonyms();
        String s2 = "";
        for (int j = 0; j < y.length; j++) s2 = s2 + y[j] + "\n";
        word.setText(myWord.getWord());
        phoneticText.setText(myWord.getPhonetic().text());
        partOfSpeech.setText(myWord.getMeaning().partOfSpeech());
        definition.setText("Definition:\n" + myWord.getMeaning().definition());
        example.setText("Example:\n" + myWord.getMeaning().example());
        synonyms.setText(s1);
        antonyms.setText(s2);
        System.out.println("Het ham");
    }

}

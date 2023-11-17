package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

import model.word.Meaning;
import model.word.Phonetic;
import model.word.Word;

import static controller.SlideMenuController.myWord;

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

    Word testword = new Word("dummy",
            new Phonetic("/həˈləʊ/", "https://api.dictionaryapi.dev"
                    + "/media/pronunciations/en/hello-uk.mp3"),
            new Meaning("noun", "\"Hello!\" or an equivalent greeting.",
                    "Hewwo!", new String[]{"donowall", "kappa"},
                    new String[]{"hmm", "goodbye"}));
    Word word1 = new Word("hiiii",
            new Phonetic("/həˈləʊ/", "https://api.dictionaryapi.dev"
                    + "/media/pronunciations/en/hello-uk.mp3"),
            new Meaning("noun", "\"Hello!\" or an equivalent greeting.",
                    "Hewwo!", new String[]{"donowall", "kappa"},
                    new String[]{"hmm", "goodbye"}));
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayWord(myWord);
    }


    public void displayWord(Word myWord) {
        String[] x = myWord.getMeaning().synonyms();
        String s1 = "";
        for (int i = 0; i < x.length; i++) s1 = s1 + x[i] + "\n";
        String[] y = myWord.getMeaning().antonyms();
        String s2 = "";
        for (int j = 0; j < y.length; j++) s2 = s2 + y[j] + "\n";
        word.setText(myWord.getWord());
        phoneticText.setText(myWord.getPhonetic().text());
        partOfSpeech.setText(myWord.getMeaning().partOfSpeech());
        definition.setText(myWord.getMeaning().definition());
        example.setText(myWord.getMeaning().example());
        synonyms.setText(s1);
        antonyms.setText(s2);
    }
}

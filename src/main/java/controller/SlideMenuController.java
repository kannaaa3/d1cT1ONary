package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import controller.MainController;
import model.dictionary.Dictionary;
import model.word.Word;


public class SlideMenuController implements Initializable {
    @FXML
    public AnchorPane mainScreen;

    @FXML
    private Button logoButton;
    @FXML
    private Button showWordButton;
    @FXML
    private Button reviewButton;
    @FXML
    private Button playButton;
    public String currentWord = "";
    public Word myWord;
    public List<String> words = new ArrayList<String>();
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        getTextFromTextField();
        myWord = dictionary.getWordData(currentWord);
        words = dictionary.getRecommendedWord(currentWord, 10);
    }

    @FXML
    private ListView<String> myListView;
    @FXML
    private TextField searchBar;


    public void displayRecommendedWordByPrefix(List<String> words) {
        myListView.getItems().addAll(words);
    }

    public void getTextFromTextField() {
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            currentWord = newValue;
            System.out.println(currentWord);
        });
    }

}

package controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.text.Font;
import javafx.util.Callback;
import model.dictionary.Dictionary;
import model.dictionary.LocalDictionary;
import model.word.Word;


import static controller.MainController.showWordController;
import static controller.ShowWordController.POPPINS_BOLD;


public class SlideMenuController implements Initializable {
    @FXML
    public AnchorPane mainScreen;

    @FXML
    public Button logoButton;
    @FXML
    public Button showWordButton;
    @FXML
    public Button reviewButton;
    @FXML
    public Button gameButton;
    public String currentWord = "";
    public static String showWord = "hello";

    @FXML
    public ListView<String> myListView;
    @FXML
    public TextField searchBar;
    @FXML
    public ImageView translate;
    @FXML
    public ImageView translateSelected;
    @FXML
    public ImageView review;
    @FXML
    public ImageView reviewSelected;
    @FXML
    public ImageView game;
    @FXML
    public ImageView gameSelected;
    @FXML
    public Label sideBarTitle;

    public List<String> words = new ArrayList<String>();
    static Dictionary dictionary = new LocalDictionary();

    public static Word myWord;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        sideBarTitle.setFont(Font.loadFont(SlideMenuController.class.getResource(POPPINS_BOLD)
                .toExternalForm(), 16));
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            currentWord = newValue;
            System.out.println(currentWord);
            words = dictionary.getRecommendedWordByPrefix(currentWord, 10);
            words.replaceAll(s -> s.replace("_", " "));
            displayRecommendedWordByPrefix(words);
        });
        searchBar.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    showWord = searchBar.getText();
                    showWordController.displayWord();
                }
            }
        });
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            showWord = t1;
            showWordController.displayWord();
        });
    }

    public void displayRecommendedWordByPrefix(List<String> words) {

        myListView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                return new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                            setStyle("-fx-control-inner-background: " + "#483D8B" + ";");
                        } else {
                            setText(item);
                            setStyle("-fx-control-inner-background: " + "#4C3E87" + ";");
                        }
                    }
                };
            }
        });
        myListView.getItems().clear();
        myListView.getItems().addAll(words);
    }

    public void getTextFromTextField() {

    }

    /**
     * Function to reset all slide menu button states.
     */
    public void resetButtonState() {
        translateSelected.setVisible(false);
        reviewSelected.setVisible(false);
        gameSelected.setVisible(false);
    }
}

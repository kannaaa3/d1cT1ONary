package com.example.demo;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class ScreenController implements Initializable {
    @FXML
    public AnchorPane mainScreen;

    @FXML
    private Button avatarButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button reviewButton;
    @FXML
    private Button playButton;

    @FXML
    private AnchorPane showWordPane;
    @FXML
    private AnchorPane reviewPane;
    @FXML
    private AnchorPane gamePane;

    @FXML
    private GameController gameController;
    @FXML
    private ReviewController reviewController;
    @FXML
    private ShowWordController showWordController;

    public static String currentWord;
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        myListView.getItems().addAll(words);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("showWord.fxml"));
            showWordPane = loader.load();
            showWordController = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("review.fxml"));
            reviewPane = loader.load();
            reviewController = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
            gamePane = loader.load();
            gameController = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setShowWordView();
        myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                currentWord = t1;
                Phonetic phonetic = new Phonetic("həˈləʊ", "//ssl.gstatic.com/diction" +
                        "ary/static/sounds/20200429/hello--_gb_1.mp3");
                String[] x = new String[]{
                        "aa", "bb", "cc"
                };
                String[] y = new String[]{
                        "dd", "ee", "ff"
                };
                Meaning meaning = new Meaning("noun", "aaa", "Hello!", x, y);
                Word word = new Word("hello", phonetic, meaning);
                showWordController.displayWord(word);
                setShowWordView();
            }
        });
    }

    @FXML
    private ListView<String> myListView;
    @FXML
    public TextField searchBar;

    String[] words = {"Dung" , "Tuong" ,"Chau", "Tran" , "Vu", "Nguyen", "Thuy", "Thi", "Minh", "Java"};

    /*
    @FXML
    private AnchorPane displayWord;

    @FXML AnchorPane searchView;
    public void setDisplayWord() {
        displayWord.setVisible(true);
    }
    */

    public void setShowWordView() {
        mainScreen.getChildren().setAll(showWordPane);
    }

    public void setReviewPane() {
        mainScreen.getChildren().setAll(reviewPane);
    }

    public void setGameView() {
        mainScreen.getChildren().setAll(gamePane);
    }


}

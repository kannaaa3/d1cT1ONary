package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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

    /*public  void setMainScreen(AnchorPane anchorPane) {
        mainScreen.getChildren().setAll(anchorPane);
    }

    @FXML
    public void setHelloPane() {
        setMainScreen(helloPane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("search.fxml"));
            helloPane = loader.load();
            helloController = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        myListView.getItems().addAll(words);
        //setShowWordView();
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

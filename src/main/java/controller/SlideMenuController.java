package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;



public class SlideMenuController implements Initializable {
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



    public static String currentWord;
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }

    @FXML
    private ListView<String> myListView;
    @FXML
    public TextField searchBar;

    public void displayRecommendedWordByPrefix(String[] words) {
        myListView.getItems().addAll(words);
    }

}

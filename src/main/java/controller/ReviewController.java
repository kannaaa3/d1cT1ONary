package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import model.word.WordList;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static controller.MainController.user;

public class ReviewController implements Initializable  {
    @FXML
    AnchorPane anchorPane;
    @FXML
    Button addNewWordlist;
    @FXML
    ImageView blurBG;
    @FXML
    AnchorPane addNewWordlistWindow;
    @FXML
    TextField creatingWordlist;
    String nameofNewWordlist;
    static int numberOfWordlist = 0;
    List<Button> buttons = new ArrayList<Button>();
    int h = 0;
    int w = 0;
    List<String> buttonNames = new ArrayList<String>();
    Image[] images = new Image[6];
    WordList newWordlist;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < 5; i++) {
            Image image = new Image("file:src\\main\\resources\\assets\\TranslateScreen\\WordlistDescription" + i + ".png");
            images[i] = image;
        }
        numberOfWordlist = user.getAllWordLists().size();
    }

    @FXML
    public void displayAddNewWordlistWindow() {
        blurBG.setVisible(true);
        addNewWordlistWindow.setVisible(true);
    }
}

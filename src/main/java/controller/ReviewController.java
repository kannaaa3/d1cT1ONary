package controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import model.user.User;
import model.word.WordList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static controller.MainController.showWordController;

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
    static int numberofWordlist = 15;
    List<Button> buttons = new ArrayList<Button>();
    int h = 0;
    int w = 0;
    List<String> buttonNames = new ArrayList<String>();
    Image[] images = new Image[6];
    WordList newWordlist;
    User user = new User("23");
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < 5; i++) {
            Image image = new Image("file:src\\main\\resources\\assets\\TranslateScreen\\WordlistDescription" + i + ".png");
            images[i] = image;
        }
        numberofWordlist = user.getAllWordLists().size();
        createWordlist(numberofWordlist);

        creatingWordlist.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    if (creatingWordlist.getText() != null) {
                        nameofNewWordlist = creatingWordlist.getText();
                    }
                    creatingWordlist.clear();
                    blurBG.setVisible(false);
                    addNewWordlistWindow.setVisible(false);
                    newWordlist = new WordList(nameofNewWordlist);
                    System.out.println(nameofNewWordlist);
                    user.getAllWordLists().add(newWordlist);
                    createWordlist(1);
                }
            }
        });
    }

    @FXML
    public void displayAddNewWordlistWindow() {
        blurBG.setVisible(true);
        addNewWordlistWindow.setVisible(true);
    }

    @FXML
    public void createWordlist(int numberofWordlist) {
        for (int i = 0; i < numberofWordlist; i++) {
            ImageView imageView = new ImageView(images[i % 5]);
            Button button = new Button();
            button.setGraphic(imageView);
            button.setContentDisplay(ContentDisplay.BOTTOM);
            button.setPrefWidth(200);
            button.setPrefHeight(200);
            button.setLayoutX(10 + (w % 3) * 220);
            w++;
            button.setLayoutY(10 + h * 220);
            if (w % 3 == 0) h++;
            button.setMinWidth(Region.USE_PREF_SIZE);
            button.setMinHeight(Region.USE_PREF_SIZE);
            button.setStyle("-fx-background-radius: 20px");
            Label label = new Label("New");
            label.setLayoutX(50 + (w % 3) * 220);
            label.setLayoutY(110 + h * 220);
            anchorPane.getChildren().add(button);
            buttons.add(button);
        }
    }
}

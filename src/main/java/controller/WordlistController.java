package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.word.Word;
import model.word.WordList;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.stage.FileChooser;

import static controller.MainController.*;

public class WordlistController implements Initializable {
    @FXML
    private AnchorPane wordlistView;
    @FXML
    public Label nameofWordlist;
    @FXML
    public Button save;
    public FileChooser fileChooser = new FileChooser();

    Image image = new Image("file:src\\main\\resources\\assets\\WordlistScreen\\NewWordlistPopup.png");
    static int h = 0;
    static int w = 0;
    public WordList wordList;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameofWordlist.setFont(Font.loadFont(ShowWordController.
                class.getResource(ShowWordController.POPPINS_REGULAR).toExternalForm(), 30));
        save.setOnAction(e -> {
            File file = fileChooser.showOpenDialog(save.getScene().getWindow());
            if (file != null) {
                wordList.writeWordListDataToFile(file.getPath());
            }
        });
    }

    public void displayWordList(WordList wordList) {
        this.wordList = wordList;
        w = 0;
        h = 0;
        wordlistView.getChildren().clear();
        for (int i = 0; i < wordList.size(); i++) {
            Label label = new Label();
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(250);
            imageView.setFitHeight(35);
            label.setFont(Font.loadFont(ShowWordController.
                    class.getResource(ShowWordController.POPPINS_REGULAR).toExternalForm(), 16));
            label.setText(wordList.getWord(i).getWord());
            label.setPrefWidth(250);
            label.setPrefHeight(35);
            label.setAlignment(Pos.BASELINE_CENTER);
            label.setTextFill(new Color(1, 1, 1, 1));
            imageView.setX(10 + (w % 2) * 270);
            label.setLayoutX(10 + (w % 2) * 270);
            w++;
            imageView.setY(10 + h * 45);
            label.setLayoutY(10 + h * 45);
            if (w % 2 == 0) h++;
            wordlistView.getChildren().add(imageView);
            wordlistView.getChildren().add(label);
        }
    }
}

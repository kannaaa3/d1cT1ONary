package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ReviewController implements Initializable  {
    @FXML
    AnchorPane anchorPane;
    static int numberofWordlist = 15;
    List<Button> buttons = new ArrayList<Button>();
    int h = 0;
    int w = 0;
    List<String> buttonNames = new ArrayList<String>();
    Image[] images = new Image[6];

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < 5; i++) {
            Image image = new Image("file:src\\main\\resources\\assets\\TranslateScreen\\WordlistDescription" + i + ".png");
            images[i] = image;
        }
        for (int i = 0; i <= numberofWordlist; i++) {
            ImageView imageView = new ImageView(images[i % 5]);
            Button button = new Button();
            button.setGraphic(imageView);
            button.setContentDisplay(ContentDisplay.BOTTOM);
            button.setPrefWidth(200);
            button.setPrefHeight(200);
            button.setLayoutX(10 + (w % 3) * 220 );
            w++;
            button.setLayoutY(10 + h * 220);
            if (w % 3 == 0) h++;
            button.setMinWidth(Region.USE_PREF_SIZE);
            button.setMinHeight(Region.USE_PREF_SIZE);
            button.setStyle("-fx-background-radius: 20px");
            anchorPane.getChildren().add(button);
            buttons.add(button);
        }
//        Button button = new Button("+");
//        button.setPrefWidth(200);
//        button.setPrefHeight(200);
//        button.setLayoutX(10 + (w % 3) * 220 );
//        w++;
//        button.setLayoutY(10 + h * 220);
//        if (w % 3 == 0) h++;
//        anchorPane.getChildren().add(button);
//        buttons.add(button);
    }
}

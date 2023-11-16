package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;
import model.dictionary.Dictionary;

import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {
        @FXML
        public GameController gameController;
        @FXML
        public ReviewController reviewController;
        @FXML
        private ShowWordController showWordController;
        @FXML
        private SlideMenuController slideMenuController;

        @FXML
        private AnchorPane showWordPane;
        @FXML
        private AnchorPane reviewPane;
        @FXML
        private AnchorPane gamePane;
        @FXML
        private AnchorPane slideMenuPane;
        @FXML
        public AnchorPane mainPane;

        Button showWordButton = new Button();
        Button reviewButton = new Button();
        Button playButton = new Button();

        @Override
        public void initialize(URL location, ResourceBundle resources) {
                Dictionary dictionary = new Dictionary("src/main/java/resources/word.txt");

                showWordButton.setPrefWidth(40);
                showWordButton.setPrefHeight(40);
                showWordButton.setLayoutX(22);
                showWordButton.setLayoutY(127);
                showWordButton.setOnAction(event -> renderShowWordScreen());
                showWordButton.setDisable(true);
                showWordButton.setOpacity(0);

                reviewButton.setPrefWidth(40);
                reviewButton.setPrefHeight(40);
                reviewButton.setLayoutX(22);
                reviewButton.setLayoutY(207);
                reviewButton.setOnAction(event -> renderReviewScreen());
                reviewButton.setDisable(true);
                reviewButton.setOpacity(0);

                playButton.setPrefWidth(40);
                playButton.setPrefHeight(40);
                playButton.setLayoutX(22);
                playButton.setLayoutY(287);
                playButton.setOnAction(event -> renderGameScreen());
                playButton.setDisable(true);
                playButton.setOpacity(0);

                showWordPane = getController("/view/showWord.fxml").getKey();
                showWordController = (ShowWordController) getController("/view/showWord.fxml").getValue();

                reviewPane = getController("/view/review.fxml").getKey();
                reviewController = (ReviewController) getController("/view/review.fxml").getValue();

                gamePane = getController("/view/game.fxml").getKey();
                gameController = (GameController) getController("/view/game.fxml").getValue();

                slideMenuPane = getController("/view/slideMenu.fxml").getKey();
                slideMenuController = (SlideMenuController) getController("/view/slideMenu.fxml").getValue();

                renderSlideMenuScreen();
                renderShowWordScreen();
                mainPane.getChildren().add(showWordButton);
                mainPane.getChildren().add(reviewButton);
                mainPane.getChildren().add(playButton);
        }

        public Pair<AnchorPane, Initializable> getController(String path) {
                try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
                        return new Pair<>( loader.load(), loader.getController());
                } catch (Exception e) {
                        e.printStackTrace();
                        System.exit(-1);
                }
                return null;
        }

        public void renderSlideMenuScreen() {
                slideMenuPane.setLayoutX(0);
                slideMenuPane.setLayoutY(0);
                mainPane.getChildren().add(slideMenuPane);
                showWordButton.setDisable(false);
                reviewButton.setDisable(false);
                playButton.setDisable(false);
        }

        public void renderShowWordScreen() {
                mainPane.getChildren().remove(showWordPane);
                showWordPane.setLayoutX(320);
                showWordPane.setLayoutY(0);
                mainPane.getChildren().add(showWordPane);
        }


        public void renderReviewScreen() {
                mainPane.getChildren().remove(reviewPane);
                reviewPane.setLayoutX(320);
                reviewPane.setLayoutY(0);
                mainPane.getChildren().add(reviewPane);
        }

        public void renderGameScreen() {
                mainPane.getChildren().remove(gamePane);
                gamePane.setLayoutX(320);
                gamePane.setLayoutY(0);
                mainPane.getChildren().add(gamePane);
        }
}

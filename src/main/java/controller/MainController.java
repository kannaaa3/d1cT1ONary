package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
        @FXML
        private GameController gameController;
        @FXML
        private ReviewController reviewController;
        @FXML
        protected ShowWordController showWordController;
        @FXML
        private SlideMenuController slideMenuController;

        @FXML
        protected AnchorPane showWordPane;
        @FXML
        private AnchorPane reviewPane;
        @FXML
        private AnchorPane gamePane;
        @FXML
        private AnchorPane slideMenuPane;
        @FXML
        private AnchorPane mainPane;


        @Override
        public void initialize(URL location, ResourceBundle resources) {
                try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/showWord.fxml"));
                        showWordPane = loader.load();
                        showWordController = loader.getController();
                } catch (Exception e) {
                        e.printStackTrace();
                }
                try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/review.fxml"));
                        reviewPane = loader.load();
                        reviewController = loader.getController();
                } catch (Exception e) {
                        e.printStackTrace();
                }
                try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/game.fxml"));
                        gamePane = loader.load();
                        gameController = loader.getController();
                } catch (Exception e) {
                        e.printStackTrace();
                }
                try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/slideMenu.fxml"));
                        slideMenuPane = loader.load();
                        slideMenuController = loader.getController();
                } catch (Exception e) {
                        e.printStackTrace();
                }
                renderSlideMenuScreeen();
                renderReviewScreen();

        }

        public void renderSlideMenuScreeen() {
                slideMenuPane.setLayoutX(0);
                slideMenuPane.setLayoutY(0);
                mainPane.getChildren().add(slideMenuPane);
        }

        public void renderShowWordScreen() {
                showWordPane.setLayoutX(320);
                showWordPane.setLayoutY(0);
                mainPane.getChildren().add(showWordPane);
        }


        public void renderReviewScreen() {
                reviewPane.setLayoutX(320);
                reviewPane.setLayoutY(0);
                mainPane.getChildren().add(reviewPane);
        }

        public void renderGameScreen() {
                gamePane.setLayoutX(320);
                gamePane.setLayoutY(0);
                mainPane.getChildren().add(gamePane);
        }
}

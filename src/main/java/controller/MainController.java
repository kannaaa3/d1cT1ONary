package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Pair;
import model.dictionary.Dictionary;
import model.user.User;
import model.word.WordList;

import java.net.URL;
import java.util.ResourceBundle;

import static controller.SlideMenuController.*;


public class MainController implements Initializable {
        @FXML
        public GameController gameController;
        @FXML
        public ReviewController reviewController;
        @FXML
        public static ShowWordController showWordController;
        @FXML
        public SlideMenuController slideMenuController;
        @FXML
        public WordlistController wordlistController;

        @FXML
        private AnchorPane showWordPane;
        @FXML
        private AnchorPane reviewPane;
        @FXML
        private AnchorPane gamePane;
        @FXML
        private AnchorPane slideMenuPane;
        @FXML
        private AnchorPane wordlistPane;
        @FXML
        public AnchorPane mainPane;


        Button showWordButton = new Button();
        Button reviewButton = new Button();
        Button playButton = new Button();
        public static User user = new User("123");
        static int j = 0;

@Override
public void initialize(URL location, ResourceBundle resources) {
        Pair<AnchorPane, Initializable> controller;
        controller = getController("/view/slideMenu.fxml");
        slideMenuPane = controller.getKey();
        slideMenuController = (SlideMenuController) controller.getValue();

        controller = getController("/view/showWord.fxml");
        showWordPane = controller.getKey();
        showWordController = (ShowWordController) controller.getValue();

        controller = getController("/view/review.fxml");
        reviewPane = controller.getKey();
        reviewController = (ReviewController) controller.getValue();

        controller = getController("/view/game.fxml");
        gamePane = controller.getKey();
        gameController = (GameController) controller.getValue();

        controller = getController("/view/wordlist.fxml");
        wordlistPane = controller.getKey();
        wordlistController = (WordlistController) controller.getValue();

        renderShowWordScreen();
        renderSlideMenuScreen();

        mainPane.getChildren().add(showWordButton);
        mainPane.getChildren().add(reviewButton);
        mainPane.getChildren().add(playButton);

        createWordlist(reviewController.numberofWordlist);

        reviewController.creatingWordlist.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                        if (keyEvent.getCode() == KeyCode.ENTER) {
                                if (reviewController.creatingWordlist.getText() != null) {
                                        reviewController.nameofNewWordlist = reviewController.creatingWordlist.getText();
                                }
                                reviewController.creatingWordlist.clear();
                                reviewController.blurBG.setVisible(false);
                                reviewController.addNewWordlistWindow.setVisible(false);
                                reviewController.newWordlist = new WordList(reviewController.nameofNewWordlist);
                                System.out.println(reviewController.nameofNewWordlist);
                                user.getAllWordLists().add(reviewController.newWordlist);
                                reviewController.numberofWordlist = user.getAllWordLists().size();
                                createWordlist(reviewController.numberofWordlist);
                        }
                }
        });

        slideMenuController.showWordButton.setOnAction(e -> renderShowWordScreen());
        slideMenuController.reviewButton.setOnAction(e -> renderReviewScreen());
        slideMenuController.gameButton.setOnAction(e -> renderGameScreen());
}

        /**
         * Function to get controller from file.
         *
         * @param path the path to the fxml file
         * @return a pair of main AnchorPane and controller
         */
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

        /**
         * Function to render show word screen.
         */
        public void renderShowWordScreen() {
                mainPane.getChildren().remove(showWordPane);
                showWordPane.setLayoutX(320);
                showWordPane.setLayoutY(0);
                mainPane.getChildren().add(showWordPane);
                hoverButton(slideMenuController.translate, slideMenuController.translateSelected);
                hoverButton(slideMenuController.reviewSelected, slideMenuController.review);
                hoverButton(slideMenuController.gameSelected, slideMenuController.game);
        }

        /**
         * Function to render review screen.
         */
        public void renderReviewScreen() {
                mainPane.getChildren().remove(reviewPane);
                reviewPane.setLayoutX(320);
                reviewPane.setLayoutY(0);
                mainPane.getChildren().add(reviewPane);
                hoverButton(slideMenuController.translateSelected, slideMenuController.translate);
                hoverButton(slideMenuController.review, slideMenuController.reviewSelected);
                hoverButton(slideMenuController.gameSelected, slideMenuController.game);
        }

        /**
         * Function to render game screen.
         */
        public void renderGameScreen() {
                mainPane.getChildren().remove(gamePane);
                gamePane.setLayoutX(320);
                gamePane.setLayoutY(0);
                mainPane.getChildren().add(gamePane);
                hoverButton(slideMenuController.translateSelected, slideMenuController.translate);
                hoverButton(slideMenuController.reviewSelected, slideMenuController.review);
                hoverButton(slideMenuController.game, slideMenuController.gameSelected);
        }

        /**
         * Function to render word list screen.
         */
        public void renderWordlistScreen() {
                mainPane.getChildren().remove(wordlistPane);
                wordlistController.nameofWordlist.setText(user.getAllWordLists().get(j).getName());
                wordlistPane.setLayoutX(320);
                wordlistPane.setLayoutY(0);
                mainPane.getChildren().add(wordlistPane);
        }

        public void hoverButton(ImageView imageView, ImageView imageViewSelected) {
                imageView.setVisible(false);
                imageViewSelected.setVisible(true);
        }

        @FXML
        public void createWordlist(int numberofWordlist) {
                while (j < numberofWordlist) {
                        ImageView imageView = new ImageView(reviewController.images[j % 5]);
                        Button button = new Button();
                        button.setGraphic(imageView);
                        button.setContentDisplay(ContentDisplay.BOTTOM);
                        button.setPrefWidth(200);
                        button.setPrefHeight(200);
                        Label label = new Label();
                        label.setText(user.getAllWordLists().get(j).getName());
                        label.setFont(new Font("Century Gothic", 20));
                        label.setTextFill(Color.WHITE);
                        label.setPrefWidth(180);
                        label.setPrefHeight(120);
                        label.setDisable(true);
                        label.setAlignment(Pos.BASELINE_CENTER);
                        button.setLayoutX(10 + (reviewController.w % 3) * 220);
                        label.setLayoutX(20 + (reviewController.w % 3) * 220);
                        reviewController.w++;
                        button.setLayoutY(10 + reviewController.h * 220);
                        label.setLayoutY(70 + reviewController.h * 220);
                        if (reviewController.w % 3 == 0) reviewController.h++;
                        button.setMinWidth(Region.USE_PREF_SIZE);
                        button.setMinHeight(Region.USE_PREF_SIZE);
                        button.setStyle("-fx-background-radius: 20px");
                        reviewController.anchorPane.getChildren().add(button);
                        reviewController.anchorPane.getChildren().add(label);
                        reviewController.buttons.add(button);
                        button.setOnAction(e -> renderWordlistScreen());
                        j++;
                }
        }
}

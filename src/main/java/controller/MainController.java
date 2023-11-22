package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Pair;
import model.database.Database;
import model.dictionary.Dictionary;
import model.user.User;
import model.word.WordList;

import java.net.URL;
import java.util.ResourceBundle;

import static controller.ShowWordController.POPPINS_BOLD;
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
        public LoginController loginController;

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
        private AnchorPane loginPane;
        @FXML
        public AnchorPane mainPane;
        public static User user;


        Button showWordButton = new Button();
        Button reviewButton = new Button();
        Button playButton = new Button();

        static int j = 0;
        static int k = 0;

        /**
         * Function to init translate button.
         */
        public void initTranslateButton() {
                showWordButton.setPrefWidth(slideMenuController.translate.getFitWidth());
                showWordButton.setPrefHeight(slideMenuController.translate.getFitHeight());
                showWordButton.setLayoutX(slideMenuController.translate.getLayoutX());
                showWordButton.setLayoutY(slideMenuController.translate.getLayoutY());
                showWordButton.setOnAction(event -> renderShowWordScreen());
                showWordButton.setDisable(true);
                showWordButton.setOpacity(0);
        }

        /**
         * Function to init review button.
         */
        public void initReviewButton() {
                reviewButton.setPrefWidth(slideMenuController.review.getFitWidth());
                reviewButton.setPrefHeight(slideMenuController.review.getFitHeight());
                reviewButton.setLayoutX(slideMenuController.review.getLayoutX());
                reviewButton.setLayoutY(slideMenuController.review.getLayoutY());
                reviewButton.setOnAction(event -> renderReviewScreen());
                reviewButton.setDisable(true);
                reviewButton.setOpacity(0);
        }

        /**
         * Function to init play button.
         */
        public void initPlayButton() {
                playButton.setPrefWidth(slideMenuController.game.getFitWidth());
                playButton.setPrefHeight(slideMenuController.game.getFitHeight());
                playButton.setLayoutX(slideMenuController.game.getLayoutX());
                playButton.setLayoutY(slideMenuController.game.getLayoutY());
                playButton.setOnAction(event -> renderGameScreen());
                playButton.setDisable(true);
                playButton.setOpacity(0);
        }

        /**
         * Function to init controller.
         */
        public void initController() {
                Pair<AnchorPane, Initializable> controller;
                controller = getController("/view/login.fxml");
                loginPane = controller.getKey();
                loginController = (LoginController) controller.getValue();

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

        }

        @Override
        public void initialize(URL location, ResourceBundle resources) {


//                renderShowWordScreen();
//                renderSlideMenuScreen();
                Pair<AnchorPane, Initializable> controller;
                controller = getController("/view/login.fxml");
                loginPane = controller.getKey();
                loginController = (LoginController) controller.getValue();
                renderLoginScreen();
                loginController.login.setOnAction(i -> {
                       if (Database.login(loginController.userNameField.getText(), loginController.passwordField.getText()) == null) {
                                loginController.userNameField.clear();
                                loginController.passwordField.clear();
                       } else {
                               user = new User(Database.login(loginController.userNameField.getText(), loginController.passwordField.getText()));
                               initController();
                               initTranslateButton();
                               initReviewButton();
                               initPlayButton();

                               renderSlideMenuScreen();
                               renderShowWordScreen();

                               mainPane.getChildren().add(showWordButton);
                               mainPane.getChildren().add(reviewButton);
                               mainPane.getChildren().add(playButton);


                               createWordlist(reviewController.numberOfWordlist);
                               creatChoicesOfWordlist(reviewController.numberOfWordlist);

                               reviewController.creatingWordlist.setOnKeyPressed(new EventHandler<KeyEvent>() {
                                       @Override
                                       public void handle(KeyEvent keyEvent) {
                                               if (keyEvent.getCode() == KeyCode.ENTER) {
                                                       if (!reviewController.creatingWordlist.getText().isEmpty()) {
                                                               reviewController.nameofNewWordlist = reviewController.creatingWordlist.getText();
                                                       } else {
                                                               return;
                                                       }
                                                       reviewController.creatingWordlist.clear();
                                                       reviewController.blurBG.setVisible(false);
                                                       reviewController.addNewWordlistWindow.setVisible(false);
                                                       System.out.println(reviewController.nameofNewWordlist);
                                                       user.createNewWordList(reviewController.nameofNewWordlist);
                                                       reviewController.numberOfWordlist = user.getAllWordLists().size();
                                                       createWordlist(reviewController.numberOfWordlist);
                                                       creatChoicesOfWordlist(reviewController.numberOfWordlist);
                                               }
                                       }
                               });
                               showWordButton.setOnAction(e -> renderShowWordScreen());
                               reviewButton.setOnAction(e -> renderReviewScreen());
                               playButton.setOnAction(e -> renderGameScreen());
                        }
                });
                loginController.registerLink.setOnAction(ee -> {
                        loginController.loginWindow.setVisible(false);
                        loginController.loginWindow.setDisable(true);
                        loginController.registerWindow.setVisible(true);
                        loginController.registerWindow.setDisable(false);
                });
                loginController.register.setOnAction(ee -> {
                        if (!loginController.userNameRegister.getText().isEmpty()
                                && !loginController.passwordRegister.getText().isEmpty()) {
                                Database.register(loginController.userNameRegister.getText(),
                                        loginController.passwordRegister.getText());
                                loginController.loginWindow.setVisible(true);
                                loginController.loginWindow.setDisable(false);
                                loginController.registerWindow.setVisible(false);
                                loginController.registerWindow.setDisable(true);
                        } else {
                                loginController.userNameRegister.clear();
                                loginController.passwordRegister.clear();
                        }
                });
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
                slideMenuController.resetButtonState();
                slideMenuController.translateSelected.setVisible(true);
        }

        /**
         * Function to render review screen.
         */
        public void renderReviewScreen() {
                mainPane.getChildren().remove(reviewPane);
                reviewPane.setLayoutX(320);
                reviewPane.setLayoutY(0);
                mainPane.getChildren().add(reviewPane);
                slideMenuController.resetButtonState();
                slideMenuController.reviewSelected.setVisible(true);
        }

        /**
         * Function to render game screen.
         */
        public void renderGameScreen() {
                mainPane.getChildren().remove(gamePane);
                gamePane.setLayoutX(320);
                gamePane.setLayoutY(0);
                mainPane.getChildren().add(gamePane);
                slideMenuController.resetButtonState();
                slideMenuController.gameSelected.setVisible(true);
        }

        /**
         * Function to render word list screen.
         */
        public void renderWordlistScreen(int wordListID) {
                System.out.println(wordListID);
                WordList wordList = user.getAllWordLists().get(wordListID);
                mainPane.getChildren().remove(wordlistPane);
                wordlistController.displayWordList(wordList);
                wordlistPane.setLayoutX(320);
                wordlistPane.setLayoutY(0);
                mainPane.getChildren().add(wordlistPane);
                wordlistController.nameofWordlist.setText(wordList.getName());
        }

        public void renderLoginScreen() {
                mainPane.getChildren().remove(loginPane);
                loginPane.setLayoutX(0);
                loginPane.setLayoutY(0);
                mainPane.getChildren().add(loginPane);
        }

        private Label getWordListLabelObject(String wordListName) {
                Label label = new Label();
                label.setText(wordListName);
                label.setFont(Font.loadFont(MainController.class.getResource(POPPINS_BOLD)
                        .toExternalForm(), 20));
                label.setTextFill(Color.WHITE);
                label.setPrefWidth(180);
                label.setPrefHeight(120);
                label.setDisable(true);
                label.setAlignment(Pos.BASELINE_CENTER);
                label.setLayoutX(20 + (reviewController.w % 3) * 220);
                label.setLayoutY(70 + reviewController.h * 220);
                return label;
        }

        private Button getWordListButtonObject() {
                ImageView imageView = new ImageView(reviewController.images[j % 5]);
                Button button = new Button();
                button.setGraphic(imageView);
                button.setContentDisplay(ContentDisplay.BOTTOM);
                button.setPrefWidth(200);
                button.setPrefHeight(200);
                button.setLayoutX(10 + (reviewController.w % 3) * 220);
                button.setLayoutY(10 + reviewController.h * 220);
                button.setMinWidth(Region.USE_PREF_SIZE);
                button.setMinHeight(Region.USE_PREF_SIZE);
                button.setStyle("-fx-background-radius: 20px");
                return button;
        }

        /**
         * Function to render wordlist
         * @param numberOfWordlist the number of user's word l
         */
        @FXML
        public void createWordlist(int numberOfWordlist) {
                while (j < numberOfWordlist) {
                        Button button = getWordListButtonObject();
                        System.out.println(j);
                        reviewController.anchorPane.getChildren().add(button);
                        reviewController.anchorPane.getChildren()
                                .add(getWordListLabelObject(user.
                                        getAllWordLists().get(j).getName()));
                        reviewController.buttons.add(button);
                        button.setId(String.valueOf(j));
                        button.setOnAction(e -> {
                                renderWordlistScreen(Integer.parseInt(button.getId()));
                        });
                        reviewController.w++;
                        if (reviewController.w % 3 == 0) reviewController.h++;
                        j++;
                }
        }

        @FXML
        public void creatChoicesOfWordlist(int numberOfWordlist) {
                while (k < numberOfWordlist) {
                        Button button = new Button();
                        ImageView imageView = new ImageView(showWordController.image);
                        button.setGraphic(imageView);
                        button.setPrefWidth(320);
                        button.setPrefHeight(50);
                        Label label = new Label();
                        label.setText(user.getAllWordLists().get(k).getName());
                        label.setFont(new Font("Century Gothic", 20));
                        label.setTextFill(Color.WHITE);
                        label.setPrefWidth(300);
                        label.setPrefHeight(40);
                        label.setDisable(true);
                        label.setAlignment(Pos.BASELINE_CENTER);
                        button.setLayoutX(10);
                        label.setLayoutX(20);
                        button.setLayoutY(10 + showWordController.h * 100);
                        label.setLayoutY(15 + showWordController.h * 100);
                        button.setMinHeight(Region.USE_PREF_SIZE);
                        button.setMinWidth(Region.USE_PREF_SIZE);
                        k++;
                        button.setOnAction(e -> {
                                user.addWordToWordList(dictionary.getWordData(showWord), k - 1);
                                //user.getAllWordLists().get(k - 1).addWord(dictionary.getWordData(showWord));
                                showWordController.popupWindow.setVisible(false);
                                System.out.print(user.getAllWordLists().get(k - 1).getWord(0).getWord());
                        });
                        showWordController.h++;
                        showWordController.showWordlist.getChildren().add(button);
                        showWordController.showWordlist.getChildren().add(label);
                }
        }

        private void getWordListLabel() {

        }
}

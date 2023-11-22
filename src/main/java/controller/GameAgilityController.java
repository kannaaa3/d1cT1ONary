package controller;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import model.game.Game;
import model.game.agility.AgilityGame;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.*;

public class GameAgilityController implements Initializable {
    public static final int TOP_LEFT = 1;
    public static final int TOP_RIGHT = 2;
    public static final int BOTTOM_LEFT = 3;
    public static final int BOTTOM_RIGHT = 4;
    @FXML
    public AnchorPane main;
    @FXML
    public Label score;
    @FXML
    public Label questionWord;
    @FXML
    public Label choiceTopLeftLabel;
    @FXML
    public Label choiceTopRightLabel;
    @FXML
    public Label choiceBottomLeftLabel;
    @FXML
    public Label choiceBottomRightLabel;
    @FXML
    public BorderPane choiceTopLeftBorderPane;
    @FXML
    public BorderPane choiceTopRightBorderPane;
    @FXML
    public BorderPane choiceBottomLeftBorderPane;
    @FXML
    public BorderPane choiceBottomRightBorderPane;
    @FXML
    public Button choiceTopLeftButton;
    @FXML
    public Button choiceTopRightButton;
    @FXML
    public Button choiceBottomLeftButton;
    @FXML
    public Button choiceBottomRightButton;
    @FXML
    public Button replayButton;
    @FXML
    public AnchorPane endView;
    @FXML
    public Label currentScore;
    public Game game;
    public ImageView[] choice = new ImageView[5];
    public ImageView[] correctChoice = new ImageView[5];
    public ImageView[] wrongChoice = new ImageView[5];
    public ImageView[] hoverChoice = new ImageView[5];
    public Random random = new Random();

    @Override
    public void initialize(URL arg0, ResourceBundle resources) {
        setFont();
        initSpecialImageView();
        createButtonEvent();
        game = new AgilityGame();
        game.startGame();
        updateGameState();
        replayButton.setOnAction(e -> {
            endView.setVisible(false);
            endView.setDisable(true);
            game.startGame();
            updateGameState();
            currentScore.setText(game.getGameScore() + "");
        });
        createNewPlanet();
    }

    private void initSpecialImageView() {
        for (int i = 1; i <= 4; i++) {
            Image image;
            image = new Image(Objects.requireNonNull(GameAgilityController.class
                    .getResourceAsStream("/assets/Game/Elevate Agility/Choice.png")));
            choice[i] = new ImageView(image);
            choice[i].setFitHeight(90);
            choice[i].setFitWidth(420);
            image = new Image(Objects.requireNonNull(GameAgilityController.class
                    .getResourceAsStream("/assets/Game/Elevate Agility/ChoiceCorrect.png")));
            correctChoice[i] = new ImageView(image);
            correctChoice[i].setFitHeight(120);
            correctChoice[i].setFitWidth(450);
            image = new Image(Objects.requireNonNull(GameAgilityController.class
                    .getResourceAsStream("/assets/Game/Elevate Agility/ChoiceWrong.png")));
            wrongChoice[i] = new ImageView(image);
            wrongChoice[i].setFitHeight(120);
            wrongChoice[i].setFitWidth(450);
            image = new Image(Objects.requireNonNull(GameAgilityController.class
                    .getResourceAsStream("/assets/Game/Elevate Agility/ChoiceHover.png")));
            hoverChoice[i] = new ImageView(image);
            hoverChoice[i].setFitHeight(120);
            hoverChoice[i].setFitWidth(450);
        }
    }

    private void createMovingMotion(ImageView imageView) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    double currentX = imageView.getX();
                    if (currentX < 20 || game.lose()) {
                        main.getChildren().remove(imageView);
                        timer.cancel();
                    }
                    currentX -= 20;
                    imageView.setX(currentX);
                });
            }
        }, 0, 500);
    }

    private void createNewPlanet() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    int planetID = random.nextInt(0, 9);
                    System.out.println(String.format("/assets/Game/Elevate Agility/planet_0%d.png", planetID));
                    Image image = new Image(Objects.requireNonNull(GameAgilityController.class
                            .getResourceAsStream(String.format("/assets/Game/Elevate Agility/planet_0%d.png", planetID))));
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(100);
                    imageView.setFitWidth(100);
                    imageView.setX(1000);
                    imageView.setY(random.nextInt(100, 200));
                    createMovingMotion(imageView);
                    main.getChildren().add(imageView);
                });

            }
        }, 0, 4000);
    }

    public void setFont() {
        choiceTopLeftLabel.setFont(Font.loadFont(ShowWordController.class
                .getResource(ShowWordController.POPPINS_REGULAR).toExternalForm(), 30));
        choiceBottomLeftLabel.setFont(Font.loadFont(ShowWordController.class
                .getResource(ShowWordController.POPPINS_REGULAR).toExternalForm(), 30));
        choiceTopRightLabel.setFont(Font.loadFont(ShowWordController.class
                .getResource(ShowWordController.POPPINS_REGULAR).toExternalForm(), 30));
        choiceBottomRightLabel.setFont(Font.loadFont(ShowWordController.class
                .getResource(ShowWordController.POPPINS_REGULAR).toExternalForm(), 30));
        score.setFont(Font.loadFont(ShowWordController.class
                .getResource(ShowWordController.POPPINS_REGULAR).toExternalForm(), 30));
    }

    private void resetAllButton() {
        choiceTopLeftBorderPane.setCenter(choice[TOP_LEFT]);
        choiceTopRightBorderPane.setCenter(choice[TOP_RIGHT]);
        choiceBottomLeftBorderPane.setCenter(choice[BOTTOM_LEFT]);
        choiceBottomRightBorderPane.setCenter(choice[BOTTOM_RIGHT]);
    }

    private void showChoice(int option, ImageView resultChoice) {
        switch (option) {
            case TOP_LEFT -> choiceTopLeftBorderPane.setCenter(resultChoice);
            case TOP_RIGHT -> choiceTopRightBorderPane.setCenter(resultChoice);
            case BOTTOM_LEFT -> choiceBottomLeftBorderPane.setCenter(resultChoice);
            case BOTTOM_RIGHT -> choiceBottomRightBorderPane.setCenter(resultChoice);
        }
    }

    private void submitAgilityGameData(int option) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("answer", option);
        JSONObject response = game.submitData(jsonObject);
        int questionAnswer = response.getInt("questionAnswer");
        if (questionAnswer != option) {
            showChoice(option, wrongChoice[option]);
        } else {
            currentScore.setText("" + game.getGameScore());
        }
        showChoice(questionAnswer, correctChoice[questionAnswer]);
        if (questionAnswer != option) {
            game.setGameState(Game.GAME_LOSE);
            lose();
        }
        updateGameState();
    }

    private void createButtonHoverEvent(Button button, int option, BorderPane borderPane) {
        button.hoverProperty().addListener((ChangeListener<Boolean>)
                (observable, oldValue, newValue) -> {
                    if (borderPane.getCenter().equals(correctChoice[option])) {
                        return;
                    }
                    if (borderPane.getCenter().equals(wrongChoice[option])) {
                        return;
                    }
                    if (newValue) {
                        showChoice(option, hoverChoice[option]);
                    } else {
                        showChoice(option, choice[option]);
                    }
                });
    }

    public void createButtonEvent() {
        createButtonHoverEvent(choiceTopLeftButton, TOP_LEFT, choiceBottomLeftBorderPane);
        createButtonHoverEvent(choiceTopRightButton, TOP_RIGHT, choiceTopRightBorderPane);
        createButtonHoverEvent(choiceBottomLeftButton, BOTTOM_LEFT, choiceBottomLeftBorderPane);
        createButtonHoverEvent(choiceBottomRightButton, BOTTOM_RIGHT, choiceBottomRightBorderPane);
        choiceTopLeftButton.setOnAction(e -> {
            submitAgilityGameData(TOP_LEFT);
        });
        choiceTopRightButton.setOnAction(e -> {
            submitAgilityGameData(TOP_RIGHT);
        });
        choiceBottomLeftButton.setOnAction(e -> {
            submitAgilityGameData(BOTTOM_LEFT);
        });
        choiceBottomRightButton.setOnAction(e -> {
            submitAgilityGameData(BOTTOM_RIGHT);
        });
    }

    public void updateGameState() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    JSONObject currentGameData = game.getGameState();
                    if (currentGameData.get("game").equals(Game.AGILITY_GAME)) {
                        handleAgilityGame(currentGameData);
                    }
                });
            }
        }, 1000);
    }

    public void lose() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    endView.setDisable(false);
                    endView.setVisible(true);
                    score.setText("Score: " + game.getGameScore());
                });
            }
        }, 1000);
    }

    private void handleAgilityGame(JSONObject jsonObject) {
        JSONArray questionData = jsonObject.getJSONArray("questionData");
        if (questionData == null) {
            System.out.println("Something went wrong with the object!");
            return;
        }
        System.out.println(questionData);
        questionWord.setText(questionData.getString(0));
        choiceTopLeftLabel.setText(questionData.getString(TOP_LEFT));
        choiceTopRightLabel.setText(questionData.getString(TOP_RIGHT));
        choiceBottomLeftLabel.setText(questionData.getString(BOTTOM_LEFT));
        choiceBottomRightLabel.setText(questionData.getString(BOTTOM_RIGHT));
        resetAllButton();
    }
}
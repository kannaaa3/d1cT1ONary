package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import model.util.TTSHandler;

import static controller.MainController.user;
import static controller.SlideMenuController.*;


public class ShowWordController implements Initializable {
    public static final String POPPINS_BOLD = "/Poppins/Poppins-Bold.ttf";
    public static final String POPPINS_REGULAR = "/Poppins/Poppins-Regular.ttf";
    @FXML
    public AnchorPane showWordScreen;
    @FXML
    public Label word;
    @FXML
    public Label phoneticText;
    @FXML
    public Label partOfSpeech;
    @FXML
    public Label definition;
    @FXML
    public Label example;
    @FXML
    public Label synonyms;
    @FXML
    public Label antonyms;
    @FXML
    public Button add;
    @FXML
    private ImageView addWordlistBG;
    @FXML
    private Dialog<ImageView> addWordlistWindow;
    @FXML
    Button audio;
    @FXML
    AnchorPane showWordlist;
    @FXML
    AnchorPane popupWindow;
    @FXML
    Button wordlist1;
    @FXML
    Button wordlist2;
    @FXML
    Button wordlist3;
    int h = 0;
    Image image = new Image("file:src\\main\\resources\\assets\\TranslateScreen\\BGForButton.png");
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setFont();
        displayWord();
        audio.setOnAction(e -> {
            TTSHandler.playTTSOfWord(showWord);
        });
        add.setOnAction(e -> {
            popupWindow.setVisible(true);
        });
        if (user.getAllWordLists().size() == 1) {

        }
    }

    /**
     * Function to set font of all object.
     */
    public void setFont() {
        word.setFont(Font.loadFont(
                ShowWordController.class.getResource(POPPINS_BOLD)
                        .toExternalForm(), 60));
        partOfSpeech.setFont(Font.loadFont(ShowWordController.class.getResource(POPPINS_REGULAR)
                .toExternalForm(), 30));
        phoneticText.setOpacity(0.7);
        definition.setFont(Font.loadFont(ShowWordController.class
                .getResource(ShowWordController.POPPINS_REGULAR).toExternalForm(), 20));
        example.setFont(Font.loadFont(ShowWordController.class
                .getResource(ShowWordController.POPPINS_REGULAR).toExternalForm(), 20));
        synonyms.setFont(Font.loadFont(ShowWordController.class
                .getResource(ShowWordController.POPPINS_REGULAR).toExternalForm(), 16));
        antonyms.setFont(Font.loadFont(ShowWordController.class
                .getResource(ShowWordController.POPPINS_REGULAR).toExternalForm(), 16));
    }

    /**
     * Function to display word's synonym and antonym.
     *
     * @param synonym the synonym
     * @param antonym the antonym
     */
    @FXML
    public void displaySynonymAndAntonym(String[] synonym, String[] antonym) {
        StringBuilder synonymString = new StringBuilder();
        StringBuilder antonymString = new StringBuilder();
        for (String word : synonym) {
            if (word.equals(myWord.getWord())) {
                continue;
            }
            synonymString.append(word.replace("_", " "));
            synonymString.append("\n");
        }
        for (String word : antonym) {
            antonymString.append(word.replace("_", " "));
            antonymString.append("\n");
        }
        synonyms.setWrapText(true);
        synonyms.setText(synonymString.toString());
        antonyms.setWrapText(true);
        antonyms.setText(antonymString.toString());
    }

    /**
     * Function to display word.
     */
    @FXML
    public void displayWord() {
        myWord = dictionary.getWordData(showWord);
        if (myWord == null) {
            return;
        }
        displaySynonymAndAntonym(myWord.getMeaning().synonyms(), myWord.getMeaning().antonyms());
        word.setText(myWord.getWord().replace("_", " "));
        phoneticText.setText("/" + myWord.getPhonetic().text() + "/");
        partOfSpeech.setText("(" + myWord.getMeaning().partOfSpeech().toUpperCase() + ")");
        definition.setText("Definition:\n" + myWord.getMeaning().definition());
        example.setText("" + myWord.getMeaning().example());
    }
}
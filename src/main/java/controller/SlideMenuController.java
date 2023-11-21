package controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.util.Callback;
import model.dictionary.Dictionary;
import model.dictionary.LocalDictionary;
import model.word.Word;



import static controller.MainController.showWordController;


public class SlideMenuController implements Initializable {
    @FXML
    public AnchorPane mainScreen;

    @FXML
    private Button logoButton;
    @FXML
    private Button showWordButton;
    @FXML
    private Button reviewButton;
    @FXML
    private Button playButton;
    public String currentWord = "";
    public static String showWord = "transpire";

    @FXML
    public ListView<String> myListView;
    @FXML
    public TextField searchBar;
    @FXML
    public ImageView translate;
    @FXML
    public ImageView translateSelected;
    @FXML
    public ImageView review;
    @FXML
    public ImageView reviewSelected;
    @FXML
    public ImageView game;
    @FXML
    public ImageView gameSelected;


    public List<String> words = new ArrayList<String>();
    static Dictionary dictionary = new LocalDictionary();

    public static Word myWord;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            currentWord = newValue;
            System.out.println(currentWord);
            words = dictionary.getRecommendedWordByPrefix(currentWord, 10);
            words.replaceAll(s -> s.replace("_", " "));
            displayRecommendedWordByPrefix(words);
        });
        searchBar.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    showWord = searchBar.getText();
                    showWordController.displayWord();
                }
            }
        });
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            showWord = t1;
            System.out.println(showWord);
            showWordController.displayWord();
            System.out.println(showWordController);
        });
    }

    public void displayRecommendedWordByPrefix(List<String> words) {

        myListView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                return new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                            setStyle("-fx-control-inner-background: " + "#483D8B" + ";");
                        } else {
                            setText(item);
                            setStyle("-fx-control-inner-background: " + "#4C3E87" + ";");
                        }
                    }
                };
            }
        });
        myListView.getItems().clear();
        myListView.getItems().addAll(words);
    }

    public void getTextFromTextField() {

    }

}

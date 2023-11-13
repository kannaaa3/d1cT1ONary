package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReviewController implements Initializable  {

    @FXML
    private ArrayList<Button> buttons = new ArrayList<Button>();

    public static int numberofWorlist = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttons.add(new Button());
    }
}

package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    TextField userNameField;
    @FXML
    PasswordField passwordField;
    @FXML
    Button login;
    @FXML
    Button register;
    @FXML
    Hyperlink registerLink;
    @FXML
    AnchorPane loginWindow;
    @FXML
    AnchorPane registerWindow;
    @FXML
    TextField userNameRegister;
    @FXML
    PasswordField passwordRegister;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

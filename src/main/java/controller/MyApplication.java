package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


public class MyApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MyApplication.class.getResource("/view/main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1266, 800);
        stage.setTitle("D1CTATORSZ");
        stage.setScene(scene);
        Image image = new Image(MyApplication.class.getResourceAsStream("/assets/SideMenu/Logo.png"));
        stage.getIcons().add(image);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        System.exit(0);
    }
}
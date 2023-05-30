package com.example.barthing;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static sceneController SceneController;
    public static DB dbConn;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mainScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setTitle("Pharmacy Management Software");
        SceneController = new sceneController(scene);
        SceneController.addScreen("addDrugs", FXMLLoader.load(getClass().getResource( "addDrugs.fxml" )));
        SceneController.addScreen("mainScreen", FXMLLoader.load(getClass().getResource( "mainScreen.fxml" )));
        SceneController.addScreen("searchDrugs", FXMLLoader.load(getClass().getResource( "searchDrugs.fxml" )));

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        dbConn = new DB();
        dbConn.setDatabase("barThing");
        launch();
    }
}
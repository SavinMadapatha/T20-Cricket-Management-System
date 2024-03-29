package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 583, 415));
        primaryStage.show();
    }


    public static void main(String[] args) throws IOException {
        LeagueManagementSystem leagueManagementSystem = new LeagueManagementSystem();
        leagueManagementSystem.loadPLayers();
        launch(args);
    }
}
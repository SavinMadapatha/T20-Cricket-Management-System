package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class TopPlayersPgController {

    //Buttons
    @FXML
    Button bestBatsmenBtn;
    @FXML
    Button bestBowlersBtn;
    @FXML
    Button backBtn;

    //opens best batsmen page
    public void setBestBatsmenBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("bestBatsmen.fxml"));
        Parent root = loader.load();

        BestBatsmenPgController bestBatsmenPgController = loader.getController();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("BestBatsmenPage");
        stage.show();
    }

    //opens best bowlers page
    public void setBestBowlersBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("bestBowlers.fxml"));
        Parent root = loader.load();

        BestBowlersPgController bestBowlersPgController = loader.getController();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("BestBowlersPage");
        stage.show();
    }

    //back button
    public void setBackBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("homePage.fxml"));
        Parent root = loader.load();

        homePageController homePageController = loader.getController();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("HomePage");
        stage.show();
    }
}

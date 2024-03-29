package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class RemovePlayer {
    //buttons
    @FXML
    Button removePlayerBtn;
    @FXML
    Button backBtn;

    //text fields
    @FXML
    TextField playersName;
    @FXML
    TextField playersTeam;

    //remove player method
    public void setRemovePlayerBtn(ActionEvent event) throws IOException {
        String playerName = playersName.getText();
        String playerTeam = playersTeam.getText();

        //creates an object from 'LeagueManagingSystem' class
        LeagueManagementSystem leagueManagementSystem = new LeagueManagementSystem();

        //loads players data when the program runs
        leagueManagementSystem.setT20Players((ArrayList<T20Player>) leagueManagementSystem.loadPLayers().clone());
        System.out.println(leagueManagementSystem.getT20Players());

        //removes the player user wants from the 't20players' arraylist
        ArrayList<T20Player> removedPlayer = leagueManagementSystem.removePlayer(playerName, playerTeam);

        //removes the player from the arrays list and updates the arraylist
        leagueManagementSystem.setT20Players(removedPlayer);

        //updates the t20Players ArrayList
        leagueManagementSystem.savePlayers(leagueManagementSystem.getT20Players());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setContentText(playerName + " removed from " + playerTeam + " team!");
        alert.show();
    }

    //back button method
    public void setBackBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditPlayerInfo.fxml"));
        Parent root = loader.load();

        editPLayerController editPLayerController = loader.getController();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("EditPlayerPage");
        stage.show();
    }
}

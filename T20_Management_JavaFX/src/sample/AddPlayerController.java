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

public class AddPlayerController {

    //Buttons
    @FXML
    Button backBtn;
    @FXML
    Button addPlayer;

    //text fields
    @FXML
    TextField playersName;
    @FXML
    TextField playersAge;
    @FXML
    TextField playersTeam;
    @FXML
    TextField playersRole;

    LeagueManagementSystem leagueManagementSystem = new LeagueManagementSystem();

    //call the method add player method
    public void setAddPlayer(ActionEvent event) throws IOException {
        String playerName = playersName.getText();
        int playerAge = Integer.parseInt(playersAge.getText());
        String playerTeam = playersTeam.getText();
        String playerRole = playersRole.getText();

        //creates an object from 'LeagueManagingSystem' class
        LeagueManagementSystem leagueManagingSystem = new LeagueManagementSystem();

        //loads players data when the program runs
        leagueManagementSystem.setT20Players((ArrayList<T20Player>) leagueManagingSystem.loadPLayers().clone());
        System.out.println(leagueManagementSystem.getT20Players());

        if (!(playerName.equalsIgnoreCase(" ") && playerTeam.equalsIgnoreCase(" ") && playerRole.equalsIgnoreCase(" "))) {
            T20Player newPlayer = leagueManagementSystem.addPlayer(playerName, playerAge, playerTeam, playerRole); //adds a new player with the data given by the user

            //adding the player to the 'players' arraylist
            ArrayList<T20Player> tempPlayers = (ArrayList<T20Player>) leagueManagementSystem.getT20Players().clone();
            tempPlayers.add(newPlayer);
            leagueManagementSystem.setT20Players(tempPlayers);

            //updates the players arraylist
            System.out.println(leagueManagementSystem.savePlayers(leagueManagementSystem.getT20Players()));
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setContentText("New Player Added!");
        alert.show();
        
    }

    //opens the edit player info page
    public void setBackBtn(javafx.event.ActionEvent event) throws IOException {
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

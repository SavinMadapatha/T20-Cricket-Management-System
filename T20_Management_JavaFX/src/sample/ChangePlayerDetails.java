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

public class ChangePlayerDetails {

    //Buttons
    @FXML
    Button editBtn;
    @FXML
    Button backBtn;

    //Text Fields
    @FXML
    TextField playersName;
    @FXML
    TextField playersAge;
    @FXML
    TextField playersTeam;
    @FXML
    TextField playersNewRole;
    @FXML
    TextField playersNewTotalScore;
    @FXML
    TextField playersNewTotWickets;

    //edit player method
    public void setEditBtn(ActionEvent event) throws IOException {
        String playerName = playersName.getText();
        int playerAge = Integer.parseInt(playersAge.getText());
        String playerTeam = playersTeam.getText();
        String newRole = playersNewRole.getText();
        int totalScore = Integer.parseInt(playersNewTotalScore.getText());
        int totalWkts = Integer.parseInt(playersNewTotWickets.getText());

        LeagueManagementSystem leagueManagementSystem = new LeagueManagementSystem();
        //loads players data when the program runs
        leagueManagementSystem.setT20Players((ArrayList<T20Player>) leagueManagementSystem.loadPLayers().clone());

        //changing player's details with the new data entered by the user
        T20Player updatedPlayer = leagueManagementSystem.editPLayer(playerName, playerAge, playerTeam, newRole, totalScore, totalWkts);
        T20Player currentPlayer = new T20Player(playerName, playerAge, playerTeam, newRole, totalScore, totalWkts);

        if (updatedPlayer != null) {
            for (T20Player player: leagueManagementSystem.getT20Players()) {
                if (player.getName().equalsIgnoreCase(currentPlayer.getName()) && player.getTeam().equalsIgnoreCase(currentPlayer.getTeam())) {
                    int index = leagueManagementSystem.getT20Players().indexOf(player);
                    leagueManagementSystem.getT20Players().remove(index);
                    leagueManagementSystem.getT20Players().add(index, updatedPlayer);

                    //updates the t20Players ArrayList
                    leagueManagementSystem.savePlayers(leagueManagementSystem.getT20Players());

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("");
                    alert.setContentText(playerName + "'s details were edited!");
                    alert.show();
                    break;
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("");
            alert.setContentText("No such player found!");
            alert.show();
        }
    }

    //back button
    public void setBackBtn(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editPlayerInfo.fxml"));
        Parent root = loader.load();

        editPLayerController editPLayerController = loader.getController();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("EditPlayerInfoPage");
        stage.show();
    }

}

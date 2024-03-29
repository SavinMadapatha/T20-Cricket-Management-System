package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MatchSummaryPgController {

    //Buttons
    @FXML
    Button backBtn;
    @FXML
    Button match1Btn;
    @FXML
    Button match2Btn;
    @FXML
    Button match3Btn;
    @FXML
    Button match4Btn;
    @FXML
    Button semi1Btn;
    @FXML
    Button semi2Btn;
    @FXML
    Button finalMatchBtn;

    LeagueManagementSystem leagueManagementSystem = new LeagueManagementSystem();

    //first match summary alert
    public void setMatch1Btn() throws IOException {
        leagueManagementSystem.displayMatchSummary("1");
    }

    //second match summary alert
    public void setMatch2Btn() throws IOException {
        leagueManagementSystem.displayMatchSummary("2");
    }

    //third match summary alert
    public void setSemi1Btn() throws IOException {
        leagueManagementSystem.displayMatchSummary("3");
    }

    //4th match
    public void setMatch4Btn() throws IOException {
        leagueManagementSystem.displayMatchSummary("4");
    }

    //5th match
    public void setMatch3Btn() throws IOException {
        leagueManagementSystem.displayMatchSummary("5");
    }

    //6th match
    public void setSemi2Btn() throws IOException {
        leagueManagementSystem.displayMatchSummary("6");
    }

    //7th match
    public void setFinalMatchBtn() throws IOException {
        leagueManagementSystem.displayMatchSummary("7");
    }

    //back button
    public void setBackBtn(javafx.event.ActionEvent event) throws IOException {
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

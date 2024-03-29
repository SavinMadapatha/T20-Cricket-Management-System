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

public class homePageController {

    //buttons on home page
    @FXML
    Button editPlayerBtn;

    @FXML
    Button startLeagueButton;

    @FXML
    Button matchSummaryButton;

    @FXML
    Button topPlayersBtn;

    @FXML
    Button quitButton;

    //opens the edit player info page
    public void setEditPlayerBtn(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editPlayerInfo.fxml"));
        Parent root = loader.load();

        editPLayerController editPLayerController = loader.getController();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("EditPlayerPage");
        stage.show();
    }

    //opens the league page
    public void setStartLeagueButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("leaguePage.fxml"));
        Parent root = loader.load();

        LeaguePageController leaguePageController = loader.getController();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("LeaguePage");
        stage.show();
    }

    //opens match summary page
    public void setMatchSummaryButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("matchSummaryPage.fxml"));
        Parent root = loader.load();

        MatchSummaryPgController matchSummaryPgController = loader.getController();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("MatchSummaryPage");
        stage.show();
    }

    //opens top players page
    public void setTopPlayersBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("topPlayers.fxml"));
        Parent root = loader.load();

        TopPlayersPgController topPlayersPgController = loader.getController();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("TopPlayersPage");
        stage.show();
    }

    //quit button
    public void setQuitButton(ActionEvent event) {
        System.exit(69);
    }

}

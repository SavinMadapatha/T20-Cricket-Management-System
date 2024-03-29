package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//implements Initializable which allows us to use the Initialize method
public class LeaguePageController implements Initializable {

    //Buttons
    @FXML
    Button backBtn;
    @FXML
    Button startLeagueBtn;

    //table view contents
    @FXML
    TableView<T20Player> tableView;
    @FXML
    TableColumn<T20Player,String> name;
    @FXML
    TableColumn<T20Player,Integer> age;
    @FXML
    TableColumn<T20Player,String> country;
    @FXML
    TableColumn<T20Player,String> role;
    @FXML
    TableColumn<T20Player,Integer> runs;
    @FXML
    TableColumn<T20Player,Integer> wickets;




    LeagueManagementSystem leagueManagementSystem = new LeagueManagementSystem();

    public LeaguePageController() throws IOException {
    }


    ObservableList<T20Player> list = FXCollections.observableArrayList(leagueManagementSystem.loadPLayers());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setCellValueFactory(new PropertyValueFactory<T20Player, String>("name"));
        age.setCellValueFactory(new PropertyValueFactory<T20Player, Integer>("age"));
        country.setCellValueFactory(new PropertyValueFactory<T20Player, String>("team"));
        role.setCellValueFactory(new PropertyValueFactory<T20Player, String>("role"));
        runs.setCellValueFactory(new PropertyValueFactory<T20Player, Integer>("totalScore"));
        wickets.setCellValueFactory(new PropertyValueFactory<T20Player, Integer>("totalWickets"));

        tableView.setItems(list);
    }

    public void setStartLeagueBtn(ActionEvent event) throws IOException {
        leagueManagementSystem.startLeague();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setContentText(leagueManagementSystem.winners + " becomes the champions of the T20 LEAGUE 2022!");
        alert.show();
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

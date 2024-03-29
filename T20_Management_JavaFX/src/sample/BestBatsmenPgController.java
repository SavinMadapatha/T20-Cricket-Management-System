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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class BestBatsmenPgController implements Initializable {

    //Buttons
    @FXML
    Button backBtn;

    //table view contents
    @FXML
    TableView<T20Player> bestBatsmenTable;
    @FXML
    TableColumn<T20Player,String> name;
    @FXML
    TableColumn<T20Player,String> country;
    @FXML
    TableColumn<T20Player,Integer> runs;

    LeagueManagementSystem leagueManagementSystem = new LeagueManagementSystem();

    public BestBatsmenPgController() throws IOException {
    }

    ArrayList<T20Player> tempArrList = leagueManagementSystem.loadPLayers();
    ObservableList<T20Player> list = FXCollections.observableArrayList(tempArrList);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setCellValueFactory(new PropertyValueFactory<T20Player, String>("name"));
        country.setCellValueFactory(new PropertyValueFactory<T20Player, String>("team"));
        runs.setCellValueFactory(new PropertyValueFactory<T20Player, Integer>("totalScore"));


        Collections.sort(list);
        bestBatsmenTable.setItems(list);

    }

    //back button
    public void setBackBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("topPlayers.fxml"));
        Parent root = loader.load();

        TopPlayersPgController topPlayersPgController = loader.getController();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("TopPlayersPage");
        stage.show();
    }
}

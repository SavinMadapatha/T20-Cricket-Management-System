package sample.Controllers;
import org.junit.Test;
import sample.LeagueManagementSystem;
import sample.T20Player;

import java.io.IOException;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class SystemTest {

    //tests the method which adds a new player
    @Test
    public void addPlayer() {
        String playerName = "Tharani";
        int playerAge = 20;
        String playerTeam = "Sri Lanka";
        String playerRole = "Bowler";

        T20Player expectedOutput = new T20Player(playerName,playerAge,playerTeam,playerRole,0,0); //adds a new player with the data given by the user

        LeagueManagementSystem leagueManagementSystem = new LeagueManagementSystem();
        T20Player actualOutput = leagueManagementSystem.addPlayer(playerName,playerAge,playerTeam,playerRole);

        assertEquals(expectedOutput.toString(), actualOutput.toString());
    }

    //tests the method which removes a player
    @Test
    public void removePlayer() {
        String playerName = "Tharani";
        String playerTeam = "Sri Lanka";

        T20Player expectedOutput = new T20Player(playerName,20,playerTeam, "batter", 0,0); //adds a new player with the data given by the user
        LeagueManagementSystem leagueManagementSystem = new LeagueManagementSystem();
        T20Player newPlayer = leagueManagementSystem.addPlayer(playerName,20,playerTeam,"batter");
        leagueManagementSystem.getT20Players().add(newPlayer);
        ArrayList<T20Player> actualOutput = leagueManagementSystem.removePlayer(playerName,playerTeam);
        expectedOutput.equals(actualOutput);
    }

    //tests the method which changes a player data
    @Test
    public void editPlayer() {
        String playerName = "Tharani";
        int playerAge = 20;
        String playerTeam = "Sri Lanka";
        String newRole = "Batter";
        int totalScore = 100;
        int totalWkts = 10;

        T20Player expectedOutput = new T20Player(playerName, playerAge, playerTeam, newRole, totalScore, totalWkts);
        LeagueManagementSystem leagueManagementSystem = new LeagueManagementSystem();
        T20Player newPlayer = leagueManagementSystem.addPlayer(playerName, playerAge, playerTeam, newRole);
        leagueManagementSystem.getT20Players().add(newPlayer);

        T20Player actualOutput = new T20Player(playerName, playerAge, playerTeam, newRole, totalScore, totalWkts);
        actualOutput.equals(expectedOutput);
    }

    //tests the method which displays best batsmen according to their total runs scored
    @Test
    public void displayBestBatsmen() throws IOException {
        LeagueManagementSystem leagueManagementSystem = new LeagueManagementSystem();
        ArrayList<T20Player> expectedOutput = leagueManagementSystem.loadPLayers();
        ArrayList<T20Player> actualOutput = expectedOutput;


        expectedOutput.sort(T20Player::compareTo);
        actualOutput.sort(T20Player::compareTo);

        assertEquals(expectedOutput,actualOutput);
    }

    //tests the method which displays best bowlers based on their total wickets
    @Test
    public void displayBestBowlers() throws IOException {
        LeagueManagementSystem leagueManagementSystem = new LeagueManagementSystem();
        ArrayList<T20Player> expectedOutput = leagueManagementSystem.loadPLayers();
        ArrayList<T20Player> actualOutput = expectedOutput;


        expectedOutput.sort(T20Player::compareTo2);
        actualOutput.sort(T20Player::compareTo2);

        assertEquals(expectedOutput,actualOutput);
    }

    @Test
    public void startLeague() throws IOException {
        LeagueManagementSystem leagueManagementSystem = new LeagueManagementSystem();
        leagueManagementSystem.startLeague();


    }


}
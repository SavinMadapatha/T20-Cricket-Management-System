package sample;

import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class LeagueManagementSystem {
    private ArrayList<T20Player> t20Players = new ArrayList<>();
    public ArrayList<T20Player[]> countries = new ArrayList<>(); //arraylist which stores players split into countries
    private ArrayList<T20Player[]> groupA = new ArrayList<>();
    private ArrayList<T20Player[]> groupB = new ArrayList<>();
    public ArrayList<String[]> matchSummary = new ArrayList<>();

    public ArrayList<String> getTournamentStandings() {
        return tournamentStandings;
    }

    public void setTournamentStandings(ArrayList<String> tournamentStandings) {
        this.tournamentStandings = tournamentStandings;
    }

    public ArrayList<String> tournamentStandings = new ArrayList<String>();
    public String runnersUp;


    //getters & setters
    public ArrayList<T20Player> getT20Players() {
        return t20Players;
    }

    public void setT20Players(ArrayList<T20Player> t20Players) {
        this.t20Players = t20Players;
    }

    //method which adds a new player
    public T20Player addPlayer(String name, int age, String team, String role) {
        return new T20Player(name, age, team,role, 0, 0);
    }

    //method which removes a player
    public ArrayList<T20Player> removePlayer (String name, String team) {
        t20Players.removeIf(player -> player.getName().equalsIgnoreCase(name) && player.getTeam().equalsIgnoreCase(team));

        return t20Players;
    }

    //method which edits player details
    /*Assumptions - I thought when editing a player's details, his name, age and the team should remain the same
     *               only the scores and the wickets can be changed. */
    public T20Player editPLayer (String name, int age, String team, String newRole, int newScore, int newWkts) {
        T20Player updatedPlayer = null;
        for (T20Player player : t20Players) {
            if (player.getName().equalsIgnoreCase(name) && player.getTeam().equalsIgnoreCase(team)) {
                //updating the player details with the newly added details by the user
                updatedPlayer = new T20Player(name, age, team, newRole, newScore, newWkts);
                break;
            }
        }
        return updatedPlayer;
    }

    //this method splits players from the t20players arraylist into teams(countries) arrays
    public void teamsSplit(ArrayList<T20Player> t20Players) {

        T20Player[] team = new T20Player[11];
        int index = 0;
        for (T20Player player: t20Players) {
            if (index == 11) {
                index = 0;
                countries.add(team);
                team = new T20Player[11];
            }
            team[index] = player;
            index++;
        }
        countries.add(team);

        ArrayList<T20Player[]> groupA = new ArrayList<>();
        groupA.add(countries.get(0));

    }

    //this method divides the 8 teams into two groups of 4
    public void divideTeams() {
        Random random = new Random();
        for (T20Player[] country : countries) {
            int randNum = random.nextInt(2);
            if (randNum == 0){
                if (groupA.size() != 4){
                    groupA.add(country);
                }else {
                    groupB.add(country);
                }
            }else {
                if (groupB.size() != 4){
                    groupB.add(country);
                }else {
                    groupA.add(country);
                }
            }
        }
    }

    //this method starts the match
    public ArrayList<String> startMatch(T20Player[] firstBattingTeam, T20Player[] secondBattingTeam, int matchNo) throws IOException {
        int firstBattingScore = 0;
        int firstBattingWickets = 0;
        int secondBattingScore = 0;
        int secondBattingWickets = 0;
        int ballsRemaining = 121;
        int totalBallsFaced = 0;

        String[] matchSummaryl = new String[8];
        matchSummaryl[0] = String.valueOf(matchNo);
        Random random = new Random();
        //1st innings
        for (T20Player batsman: firstBattingTeam) {
            matchSummaryl[1] = firstBattingTeam[0].getTeam();
            if (ballsRemaining > 1) {
                int ballsFaced = random.nextInt(ballsRemaining);
                ballsRemaining -= ballsFaced;
                totalBallsFaced += ballsFaced;
                int runsScored = random.nextInt(50);
                firstBattingScore += runsScored;
                firstBattingWickets += 1;
                matchSummaryl[3] = String.valueOf(firstBattingWickets);

                batsman.setTotalScore(batsman.getTotalScore() + runsScored); //updates the batsman's total score

                T20Player dismissedBy = secondBattingTeam[random.nextInt(11)];
                dismissedBy.setTotalWickets(dismissedBy.getTotalWickets() + 1); //adds 1 to the total wickets of the player
                matchSummaryl[2] = String.valueOf(firstBattingScore);

                //batting summary method should be called here
//                matchSummary.set(matchNo, new String[]{batsman.getTeam(), String.valueOf(firstBattingScore)});
            }
            else break;
        }
        System.out.println(firstBattingTeam[0].getTeam() + " : Runs : " + firstBattingScore + " | Wickets : " + firstBattingWickets + " | Overs : " + totalBallsFaced/6);

        //2nd innings
        int ballsRemaining2 = 121;
        int chasingTarget = firstBattingScore;
        int totalBallsFaced2 = 0;
        matchSummaryl[4] = secondBattingTeam[0].getTeam();


        for (T20Player batsman: secondBattingTeam) {
            if (ballsRemaining2 > 1 && chasingTarget > secondBattingScore) {
                int ballsFaced = random.nextInt(ballsRemaining2);
                ballsRemaining2 -= ballsFaced;
                totalBallsFaced2 += ballsFaced;
                for (int j = 0; j < ballsFaced; j++) {
                    ballsFaced--;
                    int runsScored = random.nextInt(6);
                    secondBattingScore += runsScored;
                    if (secondBattingScore > chasingTarget)
                        break;
                    batsman.setTotalScore(batsman.getTotalScore() + runsScored);
                    T20Player dismissedBy = firstBattingTeam[random.nextInt(11)];
                    dismissedBy.setTotalWickets(dismissedBy.getTotalWickets() + 1);


                    //batting summary method should be called here
                    matchSummaryl[5] = String.valueOf(secondBattingScore);
                }
            } else
                break;
            secondBattingWickets += 1;
        }
        matchSummaryl[6] = String.valueOf(secondBattingWickets);

        System.out.println(secondBattingTeam[0].getTeam() + " : Runs : " + secondBattingScore + " | Wickets : " + secondBattingWickets + " | Overs : " + totalBallsFaced2/6);

        String winningTeam;
        String loosingTeam;
        if (firstBattingScore > secondBattingScore) {
            winningTeam = firstBattingTeam[0].getTeam();
            loosingTeam = secondBattingTeam[0].getTeam();
            runnersUp = loosingTeam;
        } else {
            winningTeam = secondBattingTeam[0].getTeam();
            loosingTeam = firstBattingTeam[0].getTeam();
            runnersUp = loosingTeam;
        }
        matchSummaryl[7] = winningTeam;
        matchSummary.add(matchSummaryl);

        ArrayList<String> returnArray = new ArrayList<>();
        returnArray.add(Arrays.toString(firstBattingTeam));
        returnArray.add(Arrays.toString(secondBattingTeam));
        returnArray.add(winningTeam);

        return returnArray;
    }

    public ArrayList<String[]> loadMatchSummary() throws IOException {

        File file = new File("matchSummary.txt");
        ArrayList<String[]> matchSummary = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        if (scanner.hasNextLine()) {
            while (scanner.hasNextLine()) {
                String loadedData = scanner.nextLine();
                matchSummary.add(loadedData.split(","));
            }
        }
        scanner.close();
        return matchSummary;
    }

    public String saveMatchSummary() throws IOException {
        loadMatchSummary();
        File file = new File("matchSummary.txt");
        if (file.createNewFile()) {
            System.out.println(file.getName() + "created!");
        } else {
            System.out.println(file.getName() + "already exists!");
        }
        FileWriter writer = new FileWriter(file);
        for (String[] info: matchSummary) {
            writer.write(info[0] + "," +
                    info[1] + "," +
                    info[2] + "," +
                    info[3] + "," +
                    info[4] + "," +
                    info[5] + "," +
                    info[6] + "," +
                    info[7]  + "\n");
        }
        writer.close();
        return "match summary updated!";
    }

    public void displayMatchSummary(String matchNo) throws IOException {
        matchSummary = loadMatchSummary();
        for (String[] matchSummaryArr: matchSummary) {
            if (matchSummaryArr[0].equalsIgnoreCase(matchNo)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Match Details");
                alert.setContentText(matchSummaryArr[1] + "  VS  " + matchSummaryArr[4] + "\n" +
                        matchSummaryArr[1] + " scored " + matchSummaryArr[2] + " runs for the loss of " + matchSummaryArr[3] + "\n" +
                        matchSummaryArr[4] + " scored " + matchSummaryArr[5] + " runs for the loss of " + matchSummaryArr[6] + "\n\n" +
                        matchSummaryArr[7].toUpperCase() + " WON THE MATCH!");
                alert.show();
            }
        }
    }

    //this method is dedicated for the toss of the match
    public String matchToss(T20Player[] team1, T20Player[] team2, int matchNo) throws IOException {
        System.out.println("\n\n=========== MATCH " + matchNo + " ===========");
        System.out.println("------------ Toss -------------");

        Random random = new Random();
        int toss = random.nextInt(2);

        System.out.println("Captain of " + team1[0].getTeam() + " calling the toss!");
        int captainsCall = random.nextInt(2);

        ArrayList<String> result = new ArrayList<>();
        if (captainsCall == toss) {
            System.out.println((team1[0].getTeam()) + " won the toss.");
            int choice = random.nextInt(2);
            if (choice == 0) {
                System.out.println(team1[0].getTeam() + " chose to bat first!");
                result = startMatch(team1,team2,matchNo);
            } else {
                System.out.println(team1[0].getTeam() + " chose to bowl first!");
                result = startMatch(team2, team1, matchNo);
            }
        } else {
            System.out.println(team2[2].getTeam() + " won the toss.");
            int choice = random.nextInt(2);
            if (choice == 0) {
                System.out.println(team2[2].getTeam() + " chose to bat first!");
                result = startMatch(team2,team1,matchNo);
            } else {
                System.out.println(team2[2].getTeam() + " chose to bowl first!");
                result = startMatch(team1, team2, matchNo);
            }
        }

        return result.get(2); //returns the winning team which is the last index of the array assigned to the result
    }

    public String winners;

    //this method starts the league
    public void startLeague() throws IOException {
        setT20Players(loadPLayers());
        for (T20Player t20Player: t20Players) {
            t20Player.setTotalScore(0);
            t20Player.setTotalWickets(0);
        }
        teamsSplit(t20Players);
        System.out.println(t20Players);
        divideTeams();

        //first match
        String firstMatchWinner = matchToss(groupA.get(0), groupA.get(1), 1);
        tournamentStandings.add( "6 | " +runnersUp + " | 0 points \n");

        //second match
        String secondMatchWinner = matchToss(groupA.get(2), groupA.get(3), 2);
        tournamentStandings.add("5 | " +runnersUp + " | 2 points \n");

        //third match
        T20Player[] match1Winner = new T20Player[11];
        T20Player[] match2Winner = new T20Player[11];
        for (T20Player[] country: countries) {
            if (country[0].getTeam().equalsIgnoreCase(firstMatchWinner)) {
                match1Winner = country;
            } else if (country[0].getTeam().equalsIgnoreCase(secondMatchWinner)) {
                match2Winner = country;
            }
        }
        String thirdMatchWinner = matchToss(match1Winner, match2Winner, 3); //this match winner qualifies for the finals
        qualifiedTeams(thirdMatchWinner);
        tournamentStandings.add("4 | " +runnersUp + " | 2 points \n");


        //fourth match
        String fourthMatchWinner = matchToss(groupB.get(0), groupB.get(1), 4);

        //fifth match
        String fifthMatchWinner = matchToss(groupB.get(2), groupB.get(3),5);

        T20Player[] match4Winner = new T20Player[11];
        T20Player[] match5Winner = new T20Player[11];
        //sixth match
        for (T20Player[] country: countries) {
            if (country[0].getTeam().equalsIgnoreCase(fourthMatchWinner)) {
                match4Winner = country;
            } else if (country[0].getTeam().equalsIgnoreCase(fifthMatchWinner)) {
                match5Winner = country;
            }
        }
        String sixthMatchWinner = matchToss(match4Winner, match5Winner, 6); //this match winner qualifies for the finals
        qualifiedTeams(sixthMatchWinner);
        tournamentStandings.add( "3 | " +runnersUp + " | 2 points \n");

        //final match
        T20Player[] qualifier1 = new T20Player[11];
        T20Player[] qualifier2 = new T20Player[11];

        for (T20Player[] country: countries) {
            if (country[0].getTeam().equalsIgnoreCase(thirdMatchWinner)) {
                qualifier1 = country;
            } else if (country[0].getTeam().equalsIgnoreCase(sixthMatchWinner)) {
                qualifier2 = country;
            }
        }
        String champions = matchToss(qualifier1, qualifier2, 7);
        winners = champions;
        System.out.println("==========" + champions.toUpperCase() + " becomes the champions of T20 league 2022! ==========");

        tournamentStandings.add(1, "2 | " +runnersUp + " | 4 points \n" );
        tournamentStandings.add(0, "1 | " +winners + " | 6 points \n" );
        System.out.println(tournamentStandings.get(0));
        System.out.println(tournamentStandings.get(2));
        System.out.println(tournamentStandings.get(3));
        System.out.println(tournamentStandings.get(4));
        System.out.println(tournamentStandings.get(5));
        System.out.println(tournamentStandings.get(1));
        savePlayers(t20Players);
        saveMatchSummary();
    }

    //this method prints out the qualifying two teams for the finals
    public static void qualifiedTeams(String team) {
        System.out.println("========== " + team + " qualifies for the Finals! ==========");
    }

    //method which saves players to a text file
    public String savePlayers(ArrayList<T20Player> t20Players) throws IOException {
        File file = new File("Players.txt");
        if (file.createNewFile()) {
            System.out.println(file.getName() + "created!");
        } else {
            System.out.println(file.getName() + "already exists!");
        }
        FileWriter writer = new FileWriter(file);
        for (T20Player player: t20Players) {
            writer.write(player.getName() + "," +
                    player.getAge() + "," +
                    player.getTeam() + "," +
                    player.getRole() + "," +
                    player.getTotalScore() + "," +
                    player.getTotalWickets() + "\n");
        }
        writer.close();
        return "players saved!";
    }

    //this method loads players when the program runs
    public ArrayList<T20Player> loadPLayers() throws IOException {
        File file = new File("Players.txt");
        ArrayList<T20Player> playersArraylist = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String loadedData = scanner.nextLine();
            String[] splitData = loadedData.split(",");
            T20Player player = new T20Player(splitData[0], Integer.parseInt(splitData[1]), splitData[2], splitData[3], Integer.parseInt(splitData[4]), Integer.parseInt(splitData[5]));
            playersArraylist.add(player);
        }
        scanner.close();
        return playersArraylist;
    }
}


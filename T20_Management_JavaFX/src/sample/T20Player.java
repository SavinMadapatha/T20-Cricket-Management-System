package sample;

public class T20Player extends Player implements Comparable<T20Player>{
    private int totalScore;
    public int totalWickets;

    //parameterized constructor
    public T20Player(String name, int age, String team, String role, int totalScore, int totalWickets) {
        super(name, age, team, role);
        this.totalScore = totalScore;
        this.totalWickets = totalWickets;
    }

    //default constructor
    public T20Player() {

    }

    //getters and setters
    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getTotalWickets() {
        return totalWickets;
    }

    public void setTotalWickets(int totalWickets) {
        this.totalWickets = totalWickets;
    }

    public String toString() {
        return "name=" + super.getName() +
                ", age=" + super.getAge() +
                ", team=" + super.getTeam() +
                ", role=" + super.getRole() +
                ", totalScore=" + getTotalScore() +
                ", totalWickets=" + getTotalWickets();
    }

    @Override
    public int compareTo(T20Player t20Player) {
            if (totalScore < t20Player.totalScore){
                return 1;
            }else {
                return -1;
            }
    }

    public int compareTo2(T20Player t20Player) {
        if (totalWickets < t20Player.totalWickets){
            return 1;
        }else {
            return -1;
        }
    }
}


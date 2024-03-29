package sample;

public class Bowler extends T20Player implements Comparable<T20Player>{
    @Override
    public int compareTo(T20Player t20Player) {
        if (totalWickets < t20Player.totalWickets){
            return 1;
        }else {
            return -1;
        }
    }
}

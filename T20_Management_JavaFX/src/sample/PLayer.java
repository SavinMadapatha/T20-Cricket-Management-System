package sample;

import java.util.Scanner;

abstract class Player {
    private String name;
    private int age;
    private String team;
    private String role;

    //parameterized constructor
    public Player(String name, int age, String team, String role) {
        this.name = name;
        this.age = age;
        this.team = team;
        this.role = role;
    }

    //default constructor
    public Player() {

    }

    //getters & setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}


package com.example.yarinproject;

public class LeaderBoard {
    private int userId;
    private String name;
    private String timePlayed;
    private String attempts;

    private LeaderBoard(){}


    public int getUserid() { return userId; }
    public void setUserid(int Userid) { this.userId = Userid; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAttempts() { return attempts; }
    public void setAttempts(String attempts) { this.attempts = attempts; }

    public String getTimePlayed() { return timePlayed; }
    public void setTimePlayed(String attempts) { this.timePlayed = attempts; }

}

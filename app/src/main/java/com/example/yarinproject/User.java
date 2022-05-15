package com.example.yarinproject;

import android.database.sqlite.SQLiteDatabase;

public class User {
    private int Userid;
    private String name;
    private String phone;
    private String email;
    private String attempts;

    public User(){}


    public int getUserid() { return Userid; }
    public void setUserid(int Userid) { this.Userid = Userid; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getphone() { return phone; }
    public void setphone(String phone) { this.phone = phone; }

    public String getemail() { return email; }
    public void setemail(String email) { this.email = email; }

    public String getAttempts() { return attempts; }

    public void setAttempts(String attempts) { this.attempts = attempts; }

}

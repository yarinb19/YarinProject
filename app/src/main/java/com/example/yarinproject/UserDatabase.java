package com.example.yarinproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserDatabase extends AppCompatActivity {
    private TextView[] date,time,win_lose,name,id;
    GameHelper database;
    SQLiteDatabase db;
    private String str;
    private  int[]indDate={R.id.Date1,R.id.Date2,R.id.Date3,R.id.Date4,R.id.Date5,R.id.Date6,R.id.Date7,R.id.Date8,R.id.Date9,R.id.Date10};
    private  int[]indTime={R.id.time1,R.id.time2,R.id.time3,R.id.time4,R.id.time5,R.id.time6,R.id.time7,R.id.time8,R.id.time9,R.id.time10};
    private  int[]indwins={R.id.win_lose1,R.id.win_lose2,R.id.win_lose3,R.id.win_lose4,R.id.win_lose5,R.id.win_lose6,R.id.win_lose7,R.id.win_lose8,R.id.win_lose9,R.id.win_lose10};
    private int[]indName={R.id.Username1,R.id.Username2,R.id.Username3,R.id.Username4,R.id.Username5,R.id.Username6,R.id.Username7,R.id.Username8,R.id.Username9,R.id.Username10};
    private int[]indId={R.id.Userid1,R.id.Userid2,R.id.Userid3,R.id.Userid4,R.id.Userid5,R.id.Userid6,R.id.Userid7,R.id.Userid8,R.id.Userid9,R.id.Userid10};
      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdatabase);
          database = new GameHelper(this);
          init();
    }
    private void init() {
       date=new TextView[10];
       time=new TextView[10];
       win_lose=new TextView[10];
       name=new TextView[10];
       id=new TextView[10];
       for(int i=0;i<10;i++) {
           date[i] = findViewById(indDate[i]);
           time[i] = findViewById(indTime[i]);
           win_lose[i] =findViewById(indwins[i]);
           id[i]=findViewById(indId[i]);
           name[i]=findViewById(indName[i]);
       }
        Cursor cursor= database.getAlldata();
        cursor.moveToFirst();
        int count=cursor.getCount(), i=count-10, j=0;
        //// && i>=(int)cursor.getCount()-10 && i<(int)cursor.getCount()
        if(count>10) {
            while(j<i){j++;cursor.moveToNext();}
            while (i >= count - 10 && i < count && !cursor.isAfterLast()) {
                    id[i - (count - 10)].setText(String.valueOf(i).toString());
                    date[i - (count - 10)].setText(cursor.getString(1));
                    time[i - (count - 10)].setText(cursor.getString(2));
                    win_lose[i - (count - 10)].setText(cursor.getString(3));
                    name[i - (count - 10)].setText(cursor.getString(4));
                cursor.moveToNext();
                i++;
            }
        }
        else{
            i=0;
            while (!cursor.isAfterLast()) {
                id[i].setText(String.valueOf(i).toString());
                date[i].setText(cursor.getString(1));
                time[i].setText(cursor.getString(2));
                win_lose[i].setText(cursor.getString(3));
                name[i].setText(cursor.getString(4));
                cursor.moveToNext();
                i++;
            }
        }
      }


    public void onClick(View view) {

      }



        public static int retlenarr(TextView[] source){
        for(int i=0;i<source.length;i++){
            if(source[i]==null)
                return i;
        }
        return 1;
        }
}
package com.example.yarinproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class LeaderBoardHelper extends SQLiteOpenHelper {
    public Context context;
    public static final String DATABASENAME= "LeaderBoard.db";
    public static final int DATABASE_VERSION=1;
    public static final String TABLE_NAME ="LeaderBoard";
    public static final String KEY_ID ="id";
    public static final String KEY_NAME="name";
    public static final String timePlayed="timePlayed";
    public static final String attempts="attempts";
    public static final String wins="wins";



    public static final String CREATE_TABLE = "CREATE TABLE " +
            TABLE_NAME + "(" + KEY_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_NAME + " TEXT,  " + timePlayed + " TEXT , "
            + attempts +" TEXT, " + wins +" TEXT, " + wins +" TEXT " + ")" ;

    public static final String DROP_TABLE =
            " DROP TABLE IF EXISTS " + TABLE_NAME + "";


    public LeaderBoardHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASENAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }
}

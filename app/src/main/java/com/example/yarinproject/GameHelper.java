package com.example.yarinproject;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class GameHelper extends SQLiteOpenHelper {
    public Context context;
    public static final String DATABASENAME= "Game1.db";
    public static final int DATABASE_VERSION=1;
    public static final String TABLE_NAME ="player1";
    public static final String KEY_ID ="id";
    public static final String KEY_DATE="date";
    public static final String KEY_TIME="time";
    public static  String KEY_WINS="wins";
    public static final String KEY_NAME="name";

    public static final String CREATE_TABLE = "CREATE TABLE " +
            TABLE_NAME + "(" + KEY_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_DATE + " TEXT,  " + KEY_TIME + " TEXT , "
            + KEY_WINS +" TEXT , "+ KEY_NAME +" TEXT " +")" ;

    public static final String DROP_TABLE =
            " DROP TABLE IF EXISTS " + TABLE_NAME + "";

    public GameHelper(@Nullable Context context) {
        super(context, DATABASENAME , null , DATABASE_VERSION);
        this.context=context;
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
    public Cursor getAlldata(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data= db.rawQuery("SELECT * FROM "+
                GameHelper.TABLE_NAME,null,null);
        return data;
    }
    public long getProfilesCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        db.close();
        return count;
    }
}

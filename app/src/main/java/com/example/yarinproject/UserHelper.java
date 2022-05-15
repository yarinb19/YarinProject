package com.example.yarinproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UserHelper extends SQLiteOpenHelper {
    public Context context;
    public static final String DATABASENAME= "User.db";
    public static final int DATABASE_VERSION=1;
    public static final String TABLE_NAME ="User";
    public static final String KEY_ID ="id";
    public static final String KEY_NAME="name";
    public static final String KEY_PHONE="phone";
    public static final String KEY_EMAIL="email";
    public static final String KEY_PASSWORD="password";

    public static final String CREATE_TABLE = "CREATE TABLE " +
            TABLE_NAME + "(" + KEY_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_NAME + " TEXT,  " + KEY_PHONE + " TEXT , "
            + KEY_EMAIL +" TEXT , "+ KEY_PASSWORD +" TEXT " +")" ;

    public static final String DROP_TABLE =
            " DROP TABLE IF EXISTS " + TABLE_NAME + "";

    public UserHelper(@Nullable Context context) {
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
}

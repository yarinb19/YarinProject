package com.example.yarinproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class registration extends AppCompatActivity implements View.OnClickListener {

    private TextView txtCaption;
    private EditText txtUserName,txtEmail,txtTelephone, txtPassword;
    private Button btnSubmit;
    private Validate validation;
     UserHelper database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        database = new UserHelper(this);
        init();
    }
    private  void init(){
        txtCaption=findViewById(R.id.txtCaption);
        txtUserName=findViewById(R.id.txtUserName);
        txtPassword=findViewById(R.id.txtPassword);
        txtEmail=findViewById(R.id.txtEmail);
        txtTelephone=findViewById(R.id.txtTelephone);

        btnSubmit=findViewById(R.id.btnSend);
        btnSubmit.setOnClickListener(this);

        validation=new Validate();
        database = new UserHelper(this);
    }

    private boolean isName(){
        String userName =txtUserName.getText().toString(),name="";
        SQLiteDatabase db =  database.getReadableDatabase();
        Cursor cursor = (Cursor) db.rawQuery("SELECT * FROM " +
                        UserHelper.TABLE_NAME,
                null , null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            name=cursor.getString(1);
            if(name.equals(userName))return true;
            else cursor.moveToNext();
        }
        return false;
    }



    @Override
    public void onClick(View view) {

        if(view==btnSubmit){
            if(validation.checkPassword(txtPassword.getText().toString())==false ||
                    validation.checkUserName(txtUserName.getText().toString())==false ||
                    validation.checkEmail(txtEmail.getText().toString())==false ||
                    validation.checkTelephone(txtTelephone.getText().toString())==false )
                Toast.makeText(this,"please enter valid data",Toast.LENGTH_LONG).show();

            else if(isName()){
                Toast.makeText(this,"This name isn't available",Toast.LENGTH_LONG).show();

            }

                else {
                SQLiteDatabase db = database.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(UserHelper.KEY_NAME , txtUserName.getText().toString());
                values.put(UserHelper.KEY_EMAIL , txtEmail.getText().toString());
                values.put(UserHelper.KEY_PASSWORD , txtPassword.getText().toString());
                values.put(UserHelper.KEY_PHONE , txtTelephone.getText().toString());
                db.insert(UserHelper.TABLE_NAME , null , values);
                db.close();

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("userName",txtUserName.getText().toString());
                editor.commit();

                Intent intent=new Intent(this,Game.class);


                startActivity(intent);
            }
        }
    }
}
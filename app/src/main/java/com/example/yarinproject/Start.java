package com.example.yarinproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Start extends AppCompatActivity implements View.OnClickListener {
    private TextView txtCaption,batteria;
    private EditText txtUserName, txtPassword;
    private Button btnLogin, btnRegistration;
    private Validate validation;
    UserHelper database;
    private Myservice battery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        database = new UserHelper(this);
        init();
    }
    private  void init(){

        batteria=findViewById(R.id.batteria);
        txtCaption=findViewById(R.id.txtCaption);
        txtUserName=findViewById(R.id.txtUserName);
        txtPassword=findViewById(R.id.txtPassword);

        btnLogin=findViewById(R.id.btnLogIn);
        btnRegistration=findViewById(R.id.btnRegistration);


        btnLogin.setOnClickListener(this);
        btnRegistration.setOnClickListener(this);

        validation=new Validate();
////////////////////////
        Intent intent=getIntent();
        String user=intent.getStringExtra("user"), email=intent.getStringExtra("email"),
                phone=intent.getStringExtra("phone"),password=intent.getStringExtra("password");
        txtUserName.setText(user);
        txtPassword.setText(password);
///////////////////////
        Intent intent1 = new Intent(this,Myservice.class);
        battery = new Myservice(this, batteria);
        battery.onStartCommand(intent1,0,1);
    }
    private boolean isUser(){
        String userName =txtUserName.getText().toString(),name="";
        String userPassword =txtPassword.getText().toString(),pass="";
        SQLiteDatabase db =  database.getReadableDatabase();
        Cursor cursor = (Cursor) db.rawQuery("SELECT * FROM " +
                        UserHelper.TABLE_NAME,
                        null , null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            name=cursor.getString(1);
            pass=cursor.getString(4);
            if(name.equals(userName)&& pass.equals(userPassword))return true;
            else cursor.moveToNext();
        }
        return false;
    }
    @Override
    public void onClick(View v) {
        if(v==btnLogin){
            if(validation.checkPassword(txtPassword.getText().toString())==false ||
                    validation.checkUserName(txtUserName.getText().toString())==false)
                Toast.makeText(this,"please enter valid data",Toast.LENGTH_LONG).show();
            else {
                boolean bool=isUser();
                if(bool)
                {
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("userName",txtUserName.getText().toString());
                    editor.commit();
                    Intent intent = new Intent(this, Game.class);
                    startActivity(intent);
                }
                else Toast.makeText(this, "please register first",Toast.LENGTH_LONG).show();
            }
        }
        if(v==btnRegistration){
            Intent intent=new Intent(this,registration.class);
            startActivity(intent);
        }

    }
}
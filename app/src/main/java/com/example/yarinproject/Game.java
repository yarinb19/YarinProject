package com.example.yarinproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Game extends AppCompatActivity implements View.OnClickListener {
    private Button btnStage1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        init();

    }
    private void init(){
        btnStage1=findViewById(R.id.btnStage11);
        btnStage1.setOnClickListener(this::onClick);
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//        String name = preferences.getString("userName", "");

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.mainmenu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId=item.getItemId();
        if(itemId==R.id.stage1){
            Intent intent=new Intent(this, Stage1.class);
            startActivity(intent);
        }
                if (itemId==R.id.returnHome){
            Intent intent=new Intent(this, Start.class);
            startActivity(intent);
        }
        if(itemId==R.id.userdb){
            Intent intent=new Intent(this,UserDatabase.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view){
        if(view==btnStage1){
            Intent intent=new Intent(this, Stage1.class);

                    startActivity(intent);

        }

    }
}
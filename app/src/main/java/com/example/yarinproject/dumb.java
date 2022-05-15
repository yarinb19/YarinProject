package com.example.yarinproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class dumb extends AppCompatActivity {

    private Button btndumb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dumb);
        init();
    }
    private void init(){
        btndumb=findViewById(R.id.btndumb);



        AlertDialog.Builder builder5 = new AlertDialog.Builder(this);
        builder5.setTitle("wrong! i knew you are a dumb one");
        builder5.setCancelable(true);

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
        builder5.setView(btndumb);

        builder5.setPositiveButton(
                "for the game click here",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {

                        Intent intent = new Intent(getApplicationContext(), Game.class);
                        startActivity(intent);
                    }
                });


        /////
        AlertDialog.Builder builder4 = new AlertDialog.Builder(this);
        builder4.setMessage("I speak without a mouth and hear without ears. I have no body, but I come alive with wind. What am I? ");
        builder4.setTitle("Then answer this riddle!");
        builder4.setCancelable(true);

       // final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
        builder4.setView(btndumb);

        builder4.setPositiveButton(
                "check answer",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if(input.getText().toString()=="echo") {
                            Intent intent = new Intent(getApplicationContext(), Game.class);
                            startActivity(intent);
                        }
                            else {
                            AlertDialog alert15 = builder5.create();
                            alert15.show();
                        }
                            }
                });


        ///////
        AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
        builder3.setMessage("you sure you want to close this window?");
        builder3.setCancelable(true);

        builder3.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) { AlertDialog alert13 = builder4.create(); alert13.show(); }
                });

        builder3.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {builder3.setCancelable(true); }
                });
        ////
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        builder2.setTitle("i knew it :-)");
        builder2.setMessage("would you like to proceed to the game?");
        builder2.setCancelable(true);

        builder2.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AlertDialog alert13 = builder3.create();
                        alert13.show();
                    }
                });
                    /////

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                        builder1.setMessage("Are you dumb");
                        builder1.setCancelable(true);

                        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener()

                    {
                        public void onClick (DialogInterface dialog,int id){
                        ;
                    }

                    });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener()

                    {
                        public void onClick (DialogInterface dialog,int id){
                        AlertDialog alert12 = builder2.create();
                        alert12.show();
                    }
                    });

                    AlertDialog alert11 = builder1.create();
        alert11.show();

                    /////////

    }
}
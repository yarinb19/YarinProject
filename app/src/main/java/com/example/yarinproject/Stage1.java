package com.example.yarinproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Stage1 extends AppCompatActivity implements View.OnClickListener {
    private ImageView Gfigure, floor1,floor2,stair1,door,door1;
    private ImageButton btnup, btnright,btnleft,btnRup,btnLup;
    private CountDownTimer countDownTimer;
    private int counter = 0,ezer=0;
    private boolean isRunning = false;
    private String GameMoves="",currentTime,currentDate,win_lose="lose";
    private TextToSpeech tts;
    private ImageUtil imageUtil;
    private String str=null;
    GameHelper database;
    SQLiteDatabase db;
    ContentValues values;




    int x, y, dy,temp = 6;
    double dx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage1);
        init();
    }

    private void init() {
        currentDate= new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

        btnright = findViewById(R.id.right);
        btnup = findViewById(R.id.up);
        btnleft=findViewById(R.id.left);
        btnRup=findViewById(R.id.diagonalRight);
        btnLup=findViewById(R.id.diagonalLeft);
        Gfigure = findViewById(R.id.imgfigure);
        floor1 = findViewById(R.id.floor1);
        floor2 = findViewById(R.id.floor2);
        stair1=findViewById(R.id.stairs1);
        door=findViewById(R.id.door);
        door1=findViewById(R.id.door1);

        imageUtil=new ImageUtil(Gfigure,floor1,floor2,stair1,door);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i==TextToSpeech.SUCCESS){
                    int res=tts.setLanguage(Locale.US);

                }
            }
        });

        btnRup.setOnClickListener(this);
        btnLup.setOnClickListener(this);
        btnleft.setOnClickListener(this);
        btnright.setOnClickListener(this);
        btnup.setOnClickListener(this);

        database = new GameHelper(this);
        values = new ContentValues();
        db = database.getWritableDatabase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.stage1menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId=item.getItemId();
        if(itemId==R.id.reset){
            Gfigure.setY(floor1.getY()-Gfigure.getHeight());
            Gfigure.setX(0);
            Gfigure.setVisibility(View.VISIBLE);
            door1.setVisibility(View.INVISIBLE);
        }
        if (itemId==R.id.returnHome){
            Intent intent=new Intent(this, Start.class);
            startActivity(intent);
        }
        if (itemId==R.id.save){
            func1();
            Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();
        }
        if(itemId==R.id.userdb){
            Intent intent=new Intent(this,UserDatabase.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if(Gfigure.getX()+Gfigure.getWidth()>door1.getX()&&Gfigure.getX()<door1.getX()+door1.getWidth())
            win_lose="win";

        if (view==btnRup){
            GameMoves=GameMoves+" right & up";
            if (Gfigure.getY() + Gfigure.getHeight() == floor1.getY()||Gfigure.getY()+Gfigure.getHeight()==stair1.getY()) {
                counter = 0;
                countDownTimer = new CountDownTimer(1000, 25) {
                    @Override
                    public void onTick(long l) {
                        if((Gfigure.getX()+Gfigure.getWidth()-25>stair1.getX()&&Gfigure.getX()+Gfigure.getWidth()-25<stair1.getX()+stair1.getWidth()&&Gfigure.getY()+Gfigure.getHeight()>stair1.getY())==false) {
                            x = (int) Gfigure.getX();
                            dx = 1.5;
                            y = (int) Gfigure.getY();
                            y -= dy;
                            x += dx;
                            Gfigure.setY(y);
                            Gfigure.setX(x);
                            counter = counter + 6;
                            if (counter == 120) {
                                dy = -6;
                            }
                            if ((Gfigure.getY() + Gfigure.getHeight() == floor1.getY())) {
                                dy = 6;

                            }

                        }
                    }
                    @Override
                    public void onFinish() {
                        dy=6;
                        if(Gfigure.getX()<stair1.getX()) {
                            Gfigure.setY(floor1.getY() - Gfigure.getHeight());
                        }
                                                                                                                                                //||Gfigure.getX()<stair1.getX()+stair1.getWidth()
                        if(Gfigure.getX()+Gfigure.getWidth()-25>stair1.getX()&&Gfigure.getX()+Gfigure.getWidth()-25<stair1.getX()+stair1.getWidth()){
                            Gfigure.setY(stair1.getY()-Gfigure.getHeight());
                        }

                        if (Gfigure.getX() + Gfigure.getWidth() - 25 > floor1.getX() + floor1.getWidth() && Gfigure.getX() < floor2.getX() && comparePixel()) {
                            Gfigure.setVisibility(View.INVISIBLE);
                        }
                    }
                }.start();
            }

        }
        if (view==btnLup){
            GameMoves=GameMoves+" left & up";
            if (Gfigure.getX() >= 15) {

                x = (int) Gfigure.getX();
                y = (int) Gfigure.getY();
                dx = 30; //560
                x -= dx;
                Gfigure.setX(x);
                if ((Gfigure.getX() + Gfigure.getWidth()) > floor1.getX() + floor1.getWidth() + Gfigure.getWidth() && Gfigure.getX() + Gfigure.getWidth() < floor2.getX() && comparePixel()) {
                    Gfigure.setVisibility(View.INVISIBLE);
                }
                if (Gfigure.getY() + Gfigure.getHeight() == floor1.getY()) {
                    counter = 0;
                    countDownTimer = new CountDownTimer(1000, 25) {
                        @Override
                        public void onTick(long l) {
                            y = (int) Gfigure.getY();
                            y -= dy;
                            Gfigure.setY(y);
                            counter = counter + 6;
                            if (counter == 120) {
                                dy = -6;
                            }
                            if ((Gfigure.getY() + Gfigure.getHeight() == floor1.getY())) {
                                dy = 6;

                            }
                        }
                        @Override
                        public void onFinish() {
                            dy=6;
                            Gfigure.setY(floor1.getY()-Gfigure.getHeight());

                        }
                    }.start();
                }

            }
            else
                Toast.makeText(this,"invalid move"  ,Toast.LENGTH_LONG).show();


        }


        if (view == btnright) {
            GameMoves=GameMoves+" right";
            if(imageUtil.Part1())
                right();
            if (imageUtil.Part2())
                right();
            if (imageUtil.Part4())
                right();
            if (imageUtil.Part3())
                Gfigure.setY(floor1.getY()+Gfigure.getHeight());
            if (imageUtil.Part5())
                right();
            if (imageUtil.Part6())
                Gfigure.setX(stair1.getX()-Gfigure.getWidth()+30);
            if(imageUtil.Part7()&& imageUtil.Part8())
                right();
            if (imageUtil.Part9()) {
                Gfigure.setX(door.getX());
                door1.setVisibility(View.VISIBLE);
                sayondoor();
            }
//            else
//                right();
//
//            if(Gfigure.getX()>(stair1.getX()+stair1.getWidth()-65)){
//                Gfigure.setY(floor2.getY()-Gfigure.getHeight());
//                GameMoves=GameMoves+"R";
//            }
//            if (stair1.getX() > (Gfigure.getX() + Gfigure.getWidth() - 25)||(Gfigure.getX() + Gfigure.getWidth() - 25)>stair1.getX()) {
//                x = (int) Gfigure.getX();
//                y = (int) Gfigure.getY();
//                dx = 50;
//                if (stair1.getX() - Gfigure.getX() - Gfigure.getWidth() < 50&&stair1.getX() - Gfigure.getX() - Gfigure.getWidth() > 0)
//                    Gfigure.setX(stair1.getX() - Gfigure.getWidth() + 25);
//                else {
//                    x += dx;
//                    Gfigure.setX(x);
//                }
//                if (Gfigure.getX() + Gfigure.getWidth() - 25 > floor1.getX() + floor1.getWidth() && Gfigure.getX() < floor2.getX() && comparePixel()) {
//                    Gfigure.setVisibility(View.INVISIBLE);
//                }
//                GameMoves=GameMoves+"R";
//            }
        }

        if (view == btnleft) {
            GameMoves=GameMoves+" left";
            if ((Gfigure.getX() >= 15&&Gfigure.getX()+Gfigure.getWidth()-25<stair1.getX())) {
                Gfigure.setY(floor1.getY()-Gfigure.getHeight());
                x = (int) Gfigure.getX();
                y = (int) Gfigure.getY();
                dx = 30;
                x -= dx;
                Gfigure.setX(x);
                if (Gfigure.getX()<floor2.getX()&&Gfigure.getX()>floor1.getX()+floor1.getWidth()) {
                Gfigure.setVisibility(View.INVISIBLE);
                }
                GameMoves=GameMoves+"L";
            }
            else if(Gfigure.getX()>stair1.getX()+stair1.getWidth()){
                if(Gfigure.getX()-stair1.getX()-stair1.getWidth()<15)
                    Gfigure.setX(stair1.getX()+stair1.getWidth());
                if(Gfigure.getX()-stair1.getX()-stair1.getWidth()>15) {
                    x = (int) Gfigure.getX();
                    y = (int) Gfigure.getY();
                    dx = 30;
                    x -= dx;
                    Gfigure.setX(x);
                }
                GameMoves=GameMoves+"L";
            }
          else
            Toast.makeText(this,"invalid move"  ,Toast.LENGTH_LONG).show();
        }

        if (view == btnup) {
            GameMoves=GameMoves+" up";
            GameMoves=GameMoves+"up";
            if (Gfigure.getY() + Gfigure.getHeight() == floor1.getY()) {
                counter = 0;
                countDownTimer = new CountDownTimer(1000, 25) {
                    @Override
                    public void onTick(long l) {
                        y = (int) Gfigure.getY();
                        y -= dy;
                        Gfigure.setY(y);
                        counter = counter + 6;
                        if (counter == 120) {
                            dy = -6;
                        }
                        if ((Gfigure.getY() + Gfigure.getHeight() == floor1.getY())) {
                            dy = 6;

                        }
                    }

                    @Override
                    public void onFinish() {
                        dy=6;
                        Gfigure.setY(floor1.getY()-Gfigure.getHeight());

                    }


                }.start();
            }
        }
//        if (imageUtil.Part2())
//            Gfigure.setY(floor1.getY()+Gfigure.getHeight());getHeight
    }

        public void right(){
            x = (int) Gfigure.getX();
            y = (int) Gfigure.getY();
            dx = 50;
            x+=dx;
            Gfigure.setX(x);

        }






    public boolean comparePixel() {

        boolean bool = false;
        dy = (int) floor1.getY();
        y = (int) (Gfigure.getY() + Gfigure.getHeight());
        if (dy == y)
            bool = true;
        return bool;
    }

    private void func1() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GameHelper.KEY_DATE , currentDate);
        values.put(GameHelper.KEY_TIME , currentTime);
        values.put(GameHelper.KEY_WINS , win_lose);
        str=preferences.getString("userName","yarin123");
        values.put(GameHelper.KEY_NAME,str);

        //values.put(GameHelper.KEY_GAMEMOVES , GameMoves);
        db.insert(GameHelper.TABLE_NAME , null , values);
        db.close();

    }
    public void sayondoor(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        str=preferences.getString("userName","yarin123");
        tts.speak("congratulation   "+"  "+  str , TextToSpeech.QUEUE_FLUSH, null);
    }
    public void sayonfall(){

    }

}

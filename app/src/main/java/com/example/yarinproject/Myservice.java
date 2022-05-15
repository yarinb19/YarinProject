package com.example.yarinproject;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.IBinder;
import android.widget.TextView;
import android.widget.Toast;

import java.security.Provider;

import androidx.annotation.Nullable;

public class Myservice extends Service {
    TextView textView;
    int bateria = 1000;
    double red=0,green=0;
    Context context;


    private BroadcastReceiver BatInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context arg0, Intent intent) {

        }
    };
    public  Myservice(Context context, TextView textView){
        this.textView=textView;
        this.context=context;
    }
    private void battery(){
        BatteryManager bm = (BatteryManager) context.getSystemService(BATTERY_SERVICE);
        bateria = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
    }
    public void onCreate() {
        super.onCreate();
        this.registerReceiver(this.BatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    public double[] color(int n){
        red=255-n*2.55;
        green=n*2.55;
        double[] arr= new double[2];
        arr[0]=red;
        arr[1]=green;
        return arr;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        battery();
        Toast.makeText(context, "Command=" + bateria + "%", Toast.LENGTH_LONG).show();
      //  stopSelf();
        textView.setText(bateria + "%");
        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

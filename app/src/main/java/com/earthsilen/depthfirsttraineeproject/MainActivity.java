package com.earthsilen.depthfirsttraineeproject;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.earthsilen.depthfirsttraineeproject.ImageZoomFromList.ImageZoom;

public class MainActivity extends AppCompatActivity {

    private static final int REFRESH_SCREEN = 1;
    private static final int REFRESH_SCREEN_SKIP = 2;
    private static final String MY_PREFS = "my_prefs";
    private static final int PICK_FROM_GALLERY = 1;
    SharedPreferences shared;
    String stringValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shared = getSharedPreferences(MY_PREFS,
                Context.MODE_PRIVATE);





//        String stringValue = shared.getString("tokenKey", null);
//
//        if(stringValue != null){
//            Intent home = new Intent(this, Homepage.class);
//            startActivity(home);
//        }else if(stringValue == null){
//            startScan();
//        }

//        if(stringValue != null){
//            Intent home = new Intent(this, Homepage.class);
//            startActivity(home);
//        }else if(stringValue == null){
//            startScan();
//        }
        startScan();
    }

    public void startScan() {
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(2500);
                    stringValue = shared.getString("tokenKey", null);
                    if (stringValue != null) {

                        hRefresh.sendEmptyMessage(REFRESH_SCREEN_SKIP);
                    } else if (stringValue == null) {
                        hRefresh.sendEmptyMessage(REFRESH_SCREEN);
                    }
//                    hRefresh.sendEmptyMessage(REFRESH_SCREEN);
                } catch (Exception e) {
                }
            }
        }.start();
    }

    Handler hRefresh = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH_SCREEN:
                    // Open ActivityForm2
                    Intent a = new Intent(MainActivity.this, Login.class);
                    startActivity(a);
                    break;
                case REFRESH_SCREEN_SKIP:
                    Intent home = new Intent(MainActivity.this, Homepage.class);
                    startActivity(home);
                    break;
                default:
                    break;
            }
        }
    };


}

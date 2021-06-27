package com.igc.capstone_automative;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    int SPLASH_SCREEN = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sp = getSharedPreferences("File",MODE_PRIVATE);

                if(sp.contains("Admin") && sp.contains("Password"))
                {
                    startActivity(new Intent(MainActivity.this,DashBoard_Activity.class));
                    finish();
                }
                else
                {
                    startActivity(new Intent(MainActivity.this,Demo.class));
                    finish();
                }


            }
        },SPLASH_SCREEN);
    }
}
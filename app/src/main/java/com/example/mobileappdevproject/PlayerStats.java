package com.example.mobileappdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class PlayerStats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_playerstats);
        System.out.println("inside player stats button clicked");
    }

    public void goBack(View v) {
        Intent intentLaunch = new Intent(PlayerStats.this, NarrativeActivity.class);
        startActivity(intentLaunch);
    }
}
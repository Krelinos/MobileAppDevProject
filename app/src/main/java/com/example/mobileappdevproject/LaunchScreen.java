package com.example.mobileappdevproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LaunchScreen extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_launch);
    }

    public void onClickNewVoyage(View v) {
        Intent intentLaunch = new Intent(LaunchScreen.this, NarrativeActivity.class);
        startActivity(intentLaunch);
    }

}
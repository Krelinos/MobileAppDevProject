package com.example.mobileappdevproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LaunchScreen extends AppCompatActivity {
    SharedPreferences sp;

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
    public void onClickLoadVoyage(View v) {
        Intent intentLaunch = new Intent(LaunchScreen.this, NarrativeActivity.class);
        startActivity(intentLaunch);

        sp = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = sp.edit();

    }

}
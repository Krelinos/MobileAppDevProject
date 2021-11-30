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

        sp = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        int plyReadSpeed = Integer.parseInt( ((EditText)findViewById( R.id.player_reading_speed_setting )).getText().toString() );
        editor.putInt( "player_reading_speed", plyReadSpeed );
        editor.putInt("story_start_id", 1);
        editor.apply();

        Intent intentLaunch = new Intent(LaunchScreen.this, NarrativeActivity.class);
        startActivity(intentLaunch);

    }

    public void onClickLoadVoyage(View v) {

        sp = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        int plyReadSpeed = Integer.parseInt( ((EditText)findViewById( R.id.player_reading_speed_setting )).getText().toString() );
        editor.putInt( "player_reading_speed", plyReadSpeed );
        editor.putInt("story_start_id", 18);
        editor.apply();

        Intent intentLaunch = new Intent(LaunchScreen.this, NarrativeActivity.class);
        startActivity(intentLaunch);

    }

}
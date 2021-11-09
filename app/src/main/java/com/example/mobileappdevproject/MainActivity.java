package com.example.mobileappdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide action bar from layout
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        //creates layout for onClick method to allow user to click anywhere on the screen to proceed to the next activity
        // (created activity_launch and LaunchScreen class as placeholders to test onClick functionality but these can be renamed if needed)
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.activity_main_layout);
        relativeLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intentLaunch = new Intent(MainActivity.this, NarrativeActivity.class);
                startActivity(intentLaunch);
            }
        });
    }
}
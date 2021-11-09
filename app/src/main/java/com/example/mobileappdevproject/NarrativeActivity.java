package com.example.mobileappdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class NarrativeActivity extends AppCompatActivity {

    private View narrativeBackground;
    private View narrativeView;
    private TextView narrativeText;
    private Button choice1;
    private Button choice2;

    private Timer myTimer;

    private int progress;
    private String initialText = "\tYou find yourself lost in the woods. There is only the company of trees and the chirping of birds." +
            "\n\nYou seem to be on a dirt path. With nowhere else to go, you decide to follow it.";
    private String secondText = "\tYou walk along the path for a few minutes. The density of the forest has stayed constant the entire time." +
            "\n\nEventually, the path splits into two separate ways. You proceed to take the...";
    private String thirdText = "\tYou make you choice and follow the path in this direction, but the path and this narrative both seem to abruptly end.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide action bar from layout
        getSupportActionBar().hide();
        setContentView(R.layout.activity_narrative_holder);

        narrativeBackground = findViewById(R.id.narrative_bg);
        narrativeView = findViewById(R.id.narrative);
        narrativeText = findViewById(R.id.textview_narrative);
        choice1 = findViewById(R.id.choice1);
        choice2 = findViewById(R.id.choice2);

        progress = 0;
        introAnimation();

        View.OnClickListener choiceIllusion = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch ( ++progress ){
                    case 1:{
                        narrativeText.setText(secondText);
                        choice1.setText("Left path");
                        choice2.setText("Right path");
                        choice2.setVisibility(View.VISIBLE);
                    }
                    break;
                    case 2:{
                        narrativeText.setText(thirdText);
                        choice1.setVisibility(View.GONE);
                        choice2.setVisibility(View.GONE);
                    }
                }
            }
        };

        narrativeText.setText(initialText);
        choice1.setText("Continue");
        choice2.setVisibility(View.GONE);
        choice1.setOnClickListener( choiceIllusion );
        choice2.setOnClickListener( choiceIllusion );
    }

    private void introAnimation() {

        narrativeBackground.setAlpha(0f);
        narrativeView.setAlpha(0f);

        Keyframe k00 = Keyframe.ofFloat( 0f, 0f );
        Keyframe k01 = Keyframe.ofFloat( 0.25f, 1f );
        Keyframe k02 = Keyframe.ofFloat( 0.75f, 1f );
        Keyframe k03 = Keyframe.ofFloat( 1f, 0.8f );

        PropertyValuesHolder pvhAlpha_BG = PropertyValuesHolder.ofKeyframe("alpha", k00, k01, k02, k03);
        ObjectAnimator alphaAnim_BG = ObjectAnimator.ofPropertyValuesHolder( narrativeBackground, pvhAlpha_BG );

        Keyframe k10 = Keyframe.ofFloat( 0f, 0f );
        Keyframe k11 = Keyframe.ofFloat( 0.75f, 0f );
        Keyframe k12 = Keyframe.ofFloat( 1f, 1f );
        PropertyValuesHolder pvhAlpha_Narrative = PropertyValuesHolder.ofKeyframe("alpha", k10, k11, k12);
        ObjectAnimator alphaAnim_Narrative = ObjectAnimator.ofPropertyValuesHolder( narrativeView, pvhAlpha_Narrative );

        alphaAnim_BG.setDuration(5000).start();
        alphaAnim_Narrative.setDuration(5000).start();

    }

}
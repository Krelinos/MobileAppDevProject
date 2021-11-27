package com.example.mobileappdevproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class NarrativeActivity extends AppCompatActivity {

    private DatabaseManager choicesDB;

    private ImageView narrativeBackground;  // Background image that is not affect by the ScrollView
    private LinearLayout narrativeLayout;           // LinearLayout inside ScrollView containing the narrative elements
    private View choiceDialog;              // Template for the TextView that displays the story text
    private View choiceButton;              // Template for the branching path buttons

    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();   // Hide action bar from layout
        setContentView(R.layout.activity_narrative);

        choicesDB = new DatabaseManager(this);

        narrativeBackground = findViewById(R.id.narrative_bg_image);
        narrativeLayout     = findViewById(R.id.narrative_layout);
        choiceDialog        = findViewById(R.id.choice_dialog);
        choiceButton        = findViewById(R.id.choice_button);

        inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);

        View newNarrativeTextView = getLayoutInflater().inflate(R.layout.choice_dialog, null);

        TextView tv = newNarrativeTextView.findViewById(R.id.choice_dialog_tv);
        tv.setText("Is this it?");

        narrativeLayout.addView( newNarrativeTextView );

        String dialog = choicesDB.selectById(1).getDialog();
        String oh = dialog.replace("\n", "\n\n");

        TextView what = findViewById(R.id.wa).findViewById(R.id.choice_dialog_tv);
        what.setText( oh );
    }

    public void onClickChoiceButton(View v) {



    }

//    private void introAnimation() {
//
//        narrativeBackground.setAlpha(0f);
//        narrativeLayout.setAlpha(0f);
//
//        Keyframe k00 = Keyframe.ofFloat( 0f, 0f );
//        Keyframe k01 = Keyframe.ofFloat( 0.25f, 1f );
//        Keyframe k02 = Keyframe.ofFloat( 0.75f, 1f );
//        Keyframe k03 = Keyframe.ofFloat( 1f, 0.8f );
//
//        PropertyValuesHolder pvhAlpha_BG = PropertyValuesHolder.ofKeyframe("alpha", k00, k01, k02, k03);
//        ObjectAnimator alphaAnim_BG = ObjectAnimator.ofPropertyValuesHolder( narrativeBackground, pvhAlpha_BG );
//
//        Keyframe k10 = Keyframe.ofFloat( 0f, 0f );
//        Keyframe k11 = Keyframe.ofFloat( 0.75f, 0f );
//        Keyframe k12 = Keyframe.ofFloat( 1f, 1f );
//        PropertyValuesHolder pvhAlpha_Narrative = PropertyValuesHolder.ofKeyframe("alpha", k10, k11, k12);
//        ObjectAnimator alphaAnim_Narrative = ObjectAnimator.ofPropertyValuesHolder( narrativeView, pvhAlpha_Narrative );
//
//        alphaAnim_BG.setDuration(5000).start();
//        alphaAnim_Narrative.setDuration(5000).start();
//
//    }

}
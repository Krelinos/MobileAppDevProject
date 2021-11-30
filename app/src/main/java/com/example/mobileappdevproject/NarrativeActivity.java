package com.example.mobileappdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class NarrativeActivity extends AppCompatActivity {

    private DatabaseManager choicesDB;

    private RelativeLayout activityNarrative;   // Main layout that holds everything except for non-game things like the menu and... yeah just the menu
    private ImageView narrativeBackground;      // Background image that is not affect by the ScrollView
    private LinearLayout narrativeLayout;       // LinearLayout inside ScrollView containing the narrative elements
    private View choiceDialog;                  // Template for the TextView that displays the story text
    private View choiceButton;                  // Template for the branching path buttons

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

//        inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
//
//        //----
//
//        View newNarrativeTextView = getLayoutInflater().inflate(R.layout.choice_dialog, null);
//
//        TextView tv = newNarrativeTextView.findViewById(R.id.choice_dialog_tv);
//        tv.setText("Is this it?");
//
//        narrativeLayout.addView( newNarrativeTextView );
//
//        String dialog = choicesDB.selectById(1).getDialog();
//        String oh = dialog.replace("\n", "\n\n");
//
//        TextView what = findViewById(R.id.wa).findViewById(R.id.choice_dialog_tv);
//        what.setText( oh );
        transitionToNewBackground( choicesDB.selectById(1) );
    }

    void populateLayoutWithChoice( Choice dialogToPopulateWith ) {

        // Dialog
        String[] sectionedDialog = dialogToPopulateWith.getDialog().split("\n");
        View[] dialogViews = new View[sectionedDialog.length];

        for( int i = 0; i < sectionedDialog.length; i++ ) {

            View newNarrativeChoiceDialog = getLayoutInflater().inflate(R.layout.choice_dialog, null);
            newNarrativeChoiceDialog.setId( dialogToPopulateWith.getId() );
            newNarrativeChoiceDialog.setAlpha( 0f );     // TextViews will be revealed slowly using fadeInTextView

            TextView tv = newNarrativeChoiceDialog.findViewById(R.id.choice_dialog_tv);
            tv.setText( sectionedDialog[i] );

            narrativeLayout.addView( newNarrativeChoiceDialog );
            dialogViews[i] = newNarrativeChoiceDialog;

        }

        // Aesthetic line, then branches
        View newAestheticLine = getLayoutInflater().inflate(R.layout.aesthetic_line, null);
        narrativeLayout.addView( newAestheticLine );

        ArrayList<Choice> choiceBranches = choicesDB.selectAllByParentId( dialogToPopulateWith.getId() );
        System.out.println( String.format("Given ParentId of %d, size of choices is %d", dialogToPopulateWith.getId(), choiceBranches.size()) );
        for( int i = 0; i < choiceBranches.size(); i++ ) {

            View newChoiceButton = getLayoutInflater().inflate( R.layout.choice_button, null );

            newChoiceButton.setId( View.generateViewId() );
            newChoiceButton.setTag( R.integer.choiceId, choiceBranches.get(i).getId() );
            narrativeLayout.addView( newChoiceButton );
            // Note: choice_button views automatically connect with onClickChoiceButton

        }

        View padding = getLayoutInflater().inflate(R.layout.choice_dialog, null);
        TextView tv = padding.findViewById(R.id.choice_dialog_tv);
        tv.setText("Padding");

        narrativeLayout.addView( padding );

        // Now slowly reveal each TextView according to their estimated reading time.
        // irisreading.com says the average reader reads 200 to 250 words per minute.

        Timer revealTimer = new Timer();
        int totalDialogReadTime = 0;

        for( int i = 0; i < dialogViews.length; i++ ) {

            int previousDialogReadTime = 0;
            if( i > 0 ) {
                View previousDialogView = dialogViews[i - 1];
                TextView previousDialogTV = previousDialogView.findViewById(R.id.choice_dialog_tv);
                int estimatedWords = previousDialogTV.getText().toString().split(" ").length;

                previousDialogReadTime = estimatedWords * 60 * 1000 / 200;
            }

            View dialogView     = dialogViews[i];
            revealTimer.schedule(new TimerTask() {
                @Override
                public void run() {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ObjectAnimator alphaAnim = ObjectAnimator.ofFloat( dialogView, "alpha", 1f );
                            alphaAnim.setDuration(300).start();
                            System.out.println("Revealing next dialog");
                        }
                    });

                }
            }, totalDialogReadTime + previousDialogReadTime);

            totalDialogReadTime += previousDialogReadTime;
        }

        // TODO: Reveal the aesthetic line and the choices. Right now, they are visible immediately
        System.out.println( Resources.getSystem().getDisplayMetrics().heightPixels );

    }

    public void transitionToNewBackground( Choice initialChoice ) {

        // This will cause narrativeLayout and narrativeBackground to become invisible,
        // revealing the black background for that "fade to black" effect.
        // When it fades back in, narrativeLayout should be clear of all dialogs and branches.
        // The narrativeBackground should be fully opaque, then it fades slightly to give
        // way to the new dialog coming in.

        // narrativeLayout
        Keyframe k00 = Keyframe.ofFloat( 0f, 1f );
        Keyframe k01 = Keyframe.ofFloat( 0.167f, 0f );
        Keyframe k02 = Keyframe.ofFloat( 0.833f, 0f );
        Keyframe k03 = Keyframe.ofFloat( 1f, 1f );

        PropertyValuesHolder pvhAlpha_activity = PropertyValuesHolder.ofKeyframe("alpha", k00, k01, k02, k03);
        ObjectAnimator alphaAnim_activity = ObjectAnimator.ofPropertyValuesHolder( narrativeLayout, pvhAlpha_activity );

        // narrativeBackground (the ImageView)
        Keyframe k10 = Keyframe.ofFloat( 0f, 0.5f );
        Keyframe k11 = Keyframe.ofFloat( 0.167f, 0f );
        Keyframe k12 = Keyframe.ofFloat( 0.33f, 0f );
        Keyframe k13 = Keyframe.ofFloat( 0.5f, 1f );
        Keyframe k14 = Keyframe.ofFloat( 0.833f, 1f );
        Keyframe k15 = Keyframe.ofFloat( 1f, 0.5f );

        PropertyValuesHolder pvhAlpha_narrativeBG = PropertyValuesHolder.ofKeyframe("alpha", k10, k11, k12, k13, k14, k15);
        ObjectAnimator alphaAnim_narrativeBG = ObjectAnimator.ofPropertyValuesHolder( narrativeBackground, pvhAlpha_narrativeBG );

        alphaAnim_activity.setDuration(10000).start();
        alphaAnim_narrativeBG.setDuration(10000).start();

        // A timer is used here to clear the dialog once the fade out is fully active
        Timer clearDialogTimer = new Timer();
        clearDialogTimer.schedule(
            new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {  // This thread stuff is a bit ridiculous
                        @Override
                        public void run() {
                            narrativeLayout.removeAllViews();

                            Resources resources = narrativeBackground.getContext().getResources();
                            String bgImage      = choicesDB.getBGImageOfId( initialChoice.getId() );

                            final int resourceId = resources.getIdentifier( bgImage, "drawable", narrativeBackground.getContext().getPackageName() );
                            System.out.println( String.format("bgImage is %s. ResourceId is %d", bgImage, resourceId ) );
                            narrativeBackground.findViewById(R.id.narrative_bg_image).setBackground( resources.getDrawable( resourceId ) );
                        }
                    });
                }
            }, 3000
        );

        clearDialogTimer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                populateLayoutWithChoice( initialChoice );
                            }
                        });
                    }
                }, 8500
        );



//        for( int i = 0; i < narrativeLayout.getChildCount(); i++ ) {
//            System.out.println(getResources().getResourceEntryName(narrativeLayout.getChildAt(i).getId()));
//            narrativeLayout.removeAllViews();
//        }

    }

    public void onClickChoiceButton(View v) {

        int parentId = ( (View)v.getParent() ).getId();     // View containing the TextView that called this method

        v.setEnabled(false);

        int selectedChoiceId = (int)((View) v.getParent()).getTag( R.integer.choiceId );
        System.out.println("Moving to choiceId " + selectedChoiceId);

        Choice selectedChoice = choicesDB.selectById( selectedChoiceId );
        String newBGImage = choicesDB.getBGImageOfId( selectedChoiceId );

        if( newBGImage != null )
            transitionToNewBackground( selectedChoice );
        else
            populateLayoutWithChoice( selectedChoice );

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

    public void statsButtonClick(View v) {
        Intent intentLaunch = new Intent(NarrativeActivity.this, PlayerStats.class);
        startActivity(intentLaunch);
        System.out.println("stats button clicked");
    }

    public void restartButtonClicked(View v) {
        Intent intentLaunch = new Intent(NarrativeActivity.this, MainActivity.class);
        startActivity(intentLaunch);
        System.out.println("restart button clicked");
    }

    public void exitButtonClicked(View v) {
        finishAffinity();
        System.out.println("exit button clicked");
    }
}
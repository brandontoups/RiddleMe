package edu.auburn.eng.csse.comp3710.spring2018.ArrayIndexOutOfBoundsException;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;

/**
 *
 * names:       Brandon Toups, Evan McCarthy
 * email:       bmt0015        esm0012
 * date:        2 May 2018
 * class:       COMP3710
 * team:        ArrayIndexOutOfBoundsException
 * package:     edu.auburn.eng.csse.comp3710.spring2018.ArrayIndexOutOfBoundsException;
 * file:        ResultFragment.java
 *
 */

public class ResultFragment extends Fragment implements View.OnClickListener {

    // the button specifying intent to go back to home page
    Button back_home_button_results;
    // results view of the score received on the last quiz, and the high score achieved
    TextView current_score_view, high_score_view;
    // sharedPreferences
    SharedPreferences sharedPreferences;
    // image shown when high score achieved
    ImageView high_score_image;
    // animation for showing and hiding high score image
    Animation animation;
    // only animate when high score achieved
    boolean animate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.result_fragment, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // shared preferences initialization
        sharedPreferences = getActivity().getSharedPreferences("User information", Context.MODE_PRIVATE);
        // the current score shared preferences just received
        int currentScoreInt = sharedPreferences.getInt("current_score", Context.MODE_PRIVATE);
        // the overall app high score shared preferences
        int highScoreInt = sharedPreferences.getInt("high_score", Context.MODE_PRIVATE);
        // the text view showing current received score
        current_score_view = (TextView) getActivity().findViewById(R.id.current_score_view);
        // the text view showing high score for app
        high_score_view = (TextView) getActivity().findViewById(R.id.high_score_view);

        // if the user has a new high score
        if (currentScoreInt >= highScoreInt) {
            // high score set to current score
            highScoreInt = currentScoreInt;
            // high score image will animate
            animate = true;

            // actual animation
            // https://stackoverflow.com/a/27441001
            high_score_image = (ImageView) getActivity().findViewById(R.id.high_score_image);
            high_score_image.setVisibility(View.VISIBLE);
            animation = new AlphaAnimation((float) 0.5, 0); // Change alpha from fully visible to invisible
            animation.setDuration(500); // duration - half a second
            animation.setInterpolator(new LinearInterpolator()); // do not alter
            // animation
            // rate
            animation.setRepeatCount(Animation.INFINITE); // Repeat animation
            // infinitely
            animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the
            // end so the button will
            // fade back in
            high_score_image.startAnimation(animation);

        }

        // set the text of current score
        current_score_view.setText("Your score: " + Integer.toString(currentScoreInt));
        // set the text of the high score
        high_score_view.setText("High score: " + Integer.toString(highScoreInt));

        // listener for button to go back to home page
        back_home_button_results = (Button) getActivity().findViewById(R.id.back_home_button_results);
        back_home_button_results.setOnClickListener(this);

        // adding new high score into high score shared preferences, this will be redundantly edited if user did not
        // receive a new high score, but redundancy is fun
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putInt("high_score", highScoreInt);
        prefsEditor.commit();

    }

    @Override
    public void onClick(View v) {
        // if the user has clicked to go home, kill quiz activity
        getActivity().finish();
        // and start home page activity
        startActivity(new Intent(getActivity(), Navigation.class));

        // if currently animating
        if (animate) {
            // stop animating
            high_score_image.clearAnimation();
        }



    }
}

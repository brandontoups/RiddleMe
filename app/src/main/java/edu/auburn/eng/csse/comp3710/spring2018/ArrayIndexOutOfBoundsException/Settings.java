package edu.auburn.eng.csse.comp3710.spring2018.ArrayIndexOutOfBoundsException;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 *
 * names:       Brandon Toups, Evan McCarthy
 * email:       bmt0015        esm0012
 * date:        2 May 2018
 * class:       COMP3710
 * team:        ArrayIndexOutOfBoundsException
 * package:     edu.auburn.eng.csse.comp3710.spring2018.ArrayIndexOutOfBoundsException;
 * file:        Settings.java
 *
 */

public class Settings extends AppCompatActivity implements View.OnClickListener {

    // buttons wanting to change name, email
    Button settings_change_name_button, settings_change_email_button;

    // buttons for submitting changes to the name, email fields
    Button settings_submit_name_button, settings_submit_email_button;

    // where you enter changes to name, email
    EditText settings_enter_name_field, settings_enter_email;

    // where you are shown current name, email, score for user
    TextView settings_name, settings_email, settings_score;

    // shared preferences
    SharedPreferences sharedPreferences;

    // button for resetting high score back to zero
    Button settings_reset_score;

    // button for going back to the menu
    Button back_to_menu_button;

    // button for muting, unmuting music
    Button mute_sound_button, unmute_sound_button;

    // button for changing avatar to Jedi, Vader
    Button change_jedi, change_vader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.settings_fragment);

        // the resources for listed name, email, score on the page
        settings_name = (TextView) findViewById(R.id.settings_name);
        settings_email= (TextView) findViewById(R.id.settings_email);
        settings_score = (TextView) findViewById(R.id.settings_score);

        // shared preferences
        sharedPreferences = getSharedPreferences("User information", Context.MODE_PRIVATE);

        // setting the name on settings page to the name stored in shared preferences
        final String nameStore = sharedPreferences.getString("user_name", "default");
        settings_name.setText("Name: " + nameStore);

        // setting the email on settings page to the email stored in shared preferences
        final String emailStore = sharedPreferences.getString("user_email", "default");
        settings_email.setText("Email: " + emailStore);

        // setting the score on settings page to the score stored in shared preferences
        int scoreStore = sharedPreferences.getInt("high_score", -1);
        String display = "High score: " + Integer.toString(scoreStore);
        settings_score.setText(display);

        // listener for wanting to change name
        settings_change_name_button = (Button) findViewById(R.id.settings_change_name_button);
        settings_change_name_button.setOnClickListener(this);

        // listener for submitting name change
        settings_submit_name_button = (Button) findViewById(R.id.settings_submit_name_button);
        settings_submit_name_button.setOnClickListener(this);

        // listener for wanting to change email
        settings_change_email_button = (Button) findViewById(R.id.settings_change_email_button);
        settings_change_email_button.setOnClickListener(this);

        // listener for submitting email change
        settings_submit_email_button = (Button) findViewById(R.id.settings_submit_email_button);
        settings_submit_email_button.setOnClickListener(this);

        // listener for wanting to reset score to zero
        settings_reset_score = (Button) findViewById(R.id.settings_reset_score);
        settings_reset_score.setOnClickListener(this);

        // listener for wanting to go back to main menu
        back_to_menu_button = (Button) findViewById(R.id.back_to_menu_button);
        back_to_menu_button.setOnClickListener(this);



        // listener for wanting to change avatar to Jedi
        change_jedi = (Button) findViewById(R.id.change_jedi);
        change_jedi.setOnClickListener(this);

        // listener for wanting to change avatar to Vader
        change_vader = (Button) findViewById(R.id.change_vader);
        change_vader.setOnClickListener(this);

        // if the app should be currently playing music now, then
        if (Navigation.playMusic) {

            // set listener for wanting to mute the sound
            mute_sound_button = (Button) findViewById(R.id.mute_sound_button);
            mute_sound_button.setOnClickListener(this);
            // and show only the mute button
            mute_sound_button.setVisibility(View.VISIBLE);
        }
        // if the app should not be playing music now, then
        else {
            // set a listener for wanting to unmute the sound
            unmute_sound_button = (Button) findViewById(R.id.unmute_sound_button);
            unmute_sound_button.setOnClickListener(this);
            // and show only the unmute button
            unmute_sound_button.setVisibility(View.VISIBLE);
        }
    }

    public void onClick(View v) {

        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();

        // fetched service for playing music
        Intent svc = Navigation.svc;

        switch(v.getId()) {
            // the user wants to change his or her name
            case R.id.settings_change_name_button:
                settings_enter_name_field = (EditText) findViewById(R.id.settings_enter_name_field);
                settings_submit_name_button = (Button) findViewById(R.id.settings_submit_name_button);
                // changing name reveals place to enter new name as well as button to submit the change
                settings_enter_name_field.setVisibility(View.VISIBLE);
                settings_submit_name_button.setVisibility(View.VISIBLE);
                break;

            // the user wants to submit the name change
            case R.id.settings_submit_name_button:
                String savedName = settings_enter_name_field.getText().toString();
                prefsEditor.putString("user_name", savedName);
                prefsEditor.commit();
                finish();
                startActivity(new Intent(this, Settings.class));
                overridePendingTransition(0, 0);
                break;

            // the user wants to change his or her email
            case R.id.settings_change_email_button:
                settings_enter_email = (EditText) findViewById(R.id.settings_enter_email);
                settings_submit_email_button = (Button) findViewById(R.id.settings_submit_email_button);
                // changing name reveals place to enter new email as well as button to submit the change
                settings_enter_email.setVisibility(View.VISIBLE);
                settings_submit_email_button.setVisibility(View.VISIBLE);
                break;

            // the user wants to submit the email change
            case R.id.settings_submit_email_button:
                String savedEmail = settings_enter_email.getText().toString();
                prefsEditor.putString("user_email", savedEmail);
                prefsEditor.commit();
                //refresh the layout
                finish();
                startActivity(new Intent(this, Settings.class));
                // so the view stays motionless while fields change
                overridePendingTransition(0, 0);
                break;

            // the user wants to reset the high score back to 0
            case R.id.settings_reset_score:
                prefsEditor.putInt("high_score", 0);
                prefsEditor.commit();
                //refresh the layout
                finish();
                startActivity(new Intent(this, Settings.class));
                // so the view stays motionless while fields change
                overridePendingTransition(0, 0);
                break;

            // the user wants to go back to the main menu
            case R.id.back_to_menu_button:
                finish();
                startActivity(new Intent(this, Navigation.class));
                //overridePendingTransition(0, 0);
                break;

            // the user wants to mute the sound
            case R.id.mute_sound_button:
                // stop service to stop playing music
                stopService(svc);
                finish();
                startActivity(new Intent(this, Settings.class));
                // sound should not be playing
                Navigation.playMusic = false;
                // so the view stays motionless while fields change
                overridePendingTransition(0, 0);
                break;

            // the user wants to unmute the sound
            case R.id.unmute_sound_button:
                // start service to play music
                startService(svc);
                finish();
                startActivity(new Intent(this, Settings.class));
                Navigation.playMusic = true;
                // so the view stays motionless while fields change
                overridePendingTransition(0, 0);
                break;

            // the user wants to change avatar to Jedi
            case R.id.change_jedi:
                // stop music (will be resumed with appropriate music upon creation of Navigation
                stopService(svc);
                prefsEditor.putString("user_avatar", "Jedi");
                prefsEditor.commit();
                finish();
                startActivity(new Intent(this, Navigation.class));
                overridePendingTransition(0,0);
                break;

            // the user wants to change avatar to Vader
            case R.id.change_vader:
                // stop music (will be resumed with appropriate music upon creation of Navigation
                stopService(svc);
                prefsEditor.putString("user_avatar", "Vader");
                prefsEditor.commit();
                finish();
                startActivity(new Intent(this, Navigation.class));
                overridePendingTransition(0,0);
                break;

            default:
                break;

        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent intent = new Intent(this,Navigation.class);

        startActivity(intent);

    }
}

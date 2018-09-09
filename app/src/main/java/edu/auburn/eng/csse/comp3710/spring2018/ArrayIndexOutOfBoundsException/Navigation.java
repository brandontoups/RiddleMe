package edu.auburn.eng.csse.comp3710.spring2018.ArrayIndexOutOfBoundsException;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 *
 * names:       Brandon Toups, Evan McCarthy
 * email:       bmt0015        esm0012
 * date:        2 May 2018
 * class:       COMP3710
 * team:        ArrayIndexOutOfBoundsException
 * package:     edu.auburn.eng.csse.comp3710.spring2018.ArrayIndexOutOfBoundsException;
 * file:        Navigation.java
 *
 */

public class Navigation extends FragmentActivity  implements View.OnClickListener {

    // the dynamically changing avatar
    ImageView in_app_avatar;

    // displaying name, email at top
    TextView in_app_name, in_app_email;

    // default entry for string sharedPreferences
    private final String emptyEntry = "N/A";

    // service for playing music
    static Intent svc;

    // is app muted or unmuted
    public static boolean playMusic;

    // button for selecting to view settings
    Button settingsSelectionButton;

    // button for selecting to play quiz
    Button quizSelectionButton;

    // shared preferences name, email, avatar decision
    String preferences_name;
    String preferences_email;
    String preferences_avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.quiz_or_settings);

        // reference from the layout file
        in_app_avatar = (ImageView) findViewById(R.id.in_app_avatar);
        in_app_name = (TextView) findViewById(R.id.in_app_name);
        in_app_email = (TextView) findViewById(R.id.in_app_email);

        // CONTEXT.MODE_PRIVATE because dealing with passwords?
        SharedPreferences sharedPreferences = getSharedPreferences("User information", Context.MODE_PRIVATE);

        //preferences saved from login screen
        preferences_name = sharedPreferences.getString("user_name", "filler name");
        preferences_email = sharedPreferences.getString("user_email", "filler email");
        preferences_avatar = sharedPreferences.getString("user_avatar", "filler avatar");

        // set the text to entered name, email on login screen
        in_app_name.setText(preferences_name);
        in_app_email.setText(preferences_email);

        // quiz?
        quizSelectionButton = (Button) findViewById(R.id.quizSelectionButton);
        quizSelectionButton.setOnClickListener(this);

        // or settings
        settingsSelectionButton = (Button) findViewById(R.id.settingsSelectionButton);
        settingsSelectionButton.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();

        // setting avatar based off of selection from login screen
        if (preferences_avatar.equals("Jedi")) {
            in_app_avatar.setImageResource(R.drawable.jedi);

            // initialization of service to start BackgroundSoundService
            // so Star Wars theme song will begin playing
            svc=new Intent(this, BackgroundSoundService.class);

        }
        else if (preferences_avatar.equals("Vader")) {
            in_app_avatar.setImageResource(R.drawable.vader);

            // Imperial March play
            svc=new Intent(this, VaderBackgroundSoundService.class);

        }


        // if the sound has been unmuted from the Settings page
        if (playMusic) {
            // start the music service
            startService(svc);
        }

    }

    public void onClick(View v) {
        switch(v.getId()) {

            case R.id.quizSelectionButton:
                Intent quizIntent = new Intent(Navigation.this, QuizActivity.class);
                startActivity(quizIntent);
                break;

            case R.id.settingsSelectionButton:
                Intent settingsIntent = new Intent(Navigation.this, Settings.class);
                startActivity(settingsIntent);
                break;

            default:
                break;
        }
    }

    boolean doubleBackToExitPressedOnce = false;

    // make sure user wants to exit on back button press
    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            return;
        }

        // upon first click of back button
        // https://stackoverflow.com/a/13578600
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        // 3 second window for double clicking the back button for exit
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // stop playing music when destroyed
        stopService(svc);
    }



}

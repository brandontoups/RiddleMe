package edu.auburn.eng.csse.comp3710.spring2018.ArrayIndexOutOfBoundsException;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

/**
 *
 * names:       Brandon Toups, Evan McCarthy
 * email:       bmt0015        esm0012
 * date:        2 May 2018
 * class:       COMP3710
 * team:        ArrayIndexOutOfBoundsException
 * package:     edu.auburn.eng.csse.comp3710.spring2018.ArrayIndexOutOfBoundsException;
 * file:        SplashActivity.java
 *
 */

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_TIME = 3000;

    Intent intent;

    //https://stackoverflow.com/questions/19491073/how-do-i-set-a-time-limit-to-my-splash-screen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        intent = new Intent(this, MainActivity.class);
        new Handler().postDelayed(new Runnable() {
            public void run() {

                startActivity(intent);
                finish();

            }
        }, SPLASH_DISPLAY_TIME);

        Navigation.playMusic = true;
    }
}

package edu.auburn.eng.csse.comp3710.spring2018.ArrayIndexOutOfBoundsException;

import android.app.Activity;
import android.app.Fragment;
//import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.ViewGroup;

/**
 *
 * names:       Brandon Toups, Evan McCarthy
 * email:       bmt0015        esm0012
 * date:        2 May 2018
 * class:       COMP3710
 * team:        ArrayIndexOutOfBoundsException
 * package:     edu.auburn.eng.csse.comp3710.spring2018.ArrayIndexOutOfBoundsException;
 * file:        QuizActivity.java
 *
 */

public class QuizActivity extends FragmentActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = getSharedPreferences("User information", Context.MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.quiz_activity_container);
        // initially set the view to QuestionFragment
        QuestionFragment newFragment = new QuestionFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.quiz_container, newFragment);
        ft.commit();
    }

    @Override
    public void onBackPressed() {

        finish();
        Intent intent = new Intent(this,Navigation.class);

        startActivity(intent);
        overridePendingTransition(0, 0);
    }




}

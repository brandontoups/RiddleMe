package edu.auburn.eng.csse.comp3710.spring2018.ArrayIndexOutOfBoundsException;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

/**
 *
 * names:       Brandon Toups, Evan McCarthy
 * email:       bmt0015        esm0012
 * date:        2 May 2018
 * class:       COMP3710
 * team:        ArrayIndexOutOfBoundsException
 * package:     edu.auburn.eng.csse.comp3710.spring2018.ArrayIndexOutOfBoundsException;
 * file:        QuestionFragment.java
 *
 */

public class QuestionFragment extends Fragment implements View.OnClickListener {

    // the current answer chosen from the radio group
    RadioButton ans;

    // sharedPreferences
    SharedPreferences sharedPreferences;

    // list of Question s
    List<Question> setOfQuestions;

    // score for this round
    int currentScore = 0;

    // for keeping user-defined limit on number of questions per round
    int numQuestionsAnswered;

    // current Question object
    Question currentQuestion;

    // the text shown on the screen
    TextView questionText;

    // each individual radio button
    RadioButton a,b,c,d;

    // to go to next question
    Button nextButton;

    // the database of all questions
    Database db;

    // radio group shown on screen
    RadioGroup radio_group;

    // for iterating through the radio group
    RadioButton cycle_radio_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.question_layout, container, false);

        sharedPreferences = getActivity().getSharedPreferences("User information",Context.MODE_PRIVATE);

        db = new Database(getActivity());

        setOfQuestions = db.retrieveQuestions();

        // pseudorandomization of questions shown
        Collections.shuffle(setOfQuestions);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // upon creation of view of new question, set the current Question to the next one in the
        // list of Questions, as numQuestionsAnswered incremented after each question.
        currentQuestion = setOfQuestions.get(numQuestionsAnswered);

        questionText = (TextView) getView().findViewById(R.id.question_text);

        a = (RadioButton) getView().findViewById(R.id.a);
        b = (RadioButton) getView().findViewById(R.id.b);
        c = (RadioButton) getView().findViewById(R.id.c);
        d = (RadioButton) getView().findViewById(R.id.d);

        setView();

        // next question button
        nextButton = (Button) getView().findViewById(R.id.nextButton);
        nextButton.setOnClickListener(this);

    }

    // for when the user clicks the next question button, it will be vaidated here
    @Override
    public void onClick(View v) {
        this.validateAnswer(v);
    }

    // initialize all the fields shown on the screen with the current Question information
    private void setView() {

        questionText.setText(currentQuestion.getQuestion());

        a.setText(currentQuestion.getAnsA());
        b.setText(currentQuestion.getAnsB());
        c.setText(currentQuestion.getAnsC());
        d.setText(currentQuestion.getAnsD());

        numQuestionsAnswered++;

        radio_group = (RadioGroup) getView().findViewById(R.id.radio_group);
        // clear the selected radio button, as this view is recycled for each question
        radio_group.clearCheck();

    }

    public void validateAnswer(View view) {

        radio_group = (RadioGroup) getView().findViewById(R.id.radio_group);
        // the currently selected radio button
        ans = (RadioButton) getView().findViewById(radio_group.getCheckedRadioButtonId());

        // if nothing clicked
        if (radio_group.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getActivity(),
                    "No answer selected", Toast.LENGTH_SHORT).show();
        }

        // else this is the actual validation of answer selected vs Question's member answer
        else {
            // if the currently selected radio button has text that .equals() the current Question's answer
            if (currentQuestion.getAnswer().equals(ans.getText())) {
                // the user has scored one point
                currentScore++;

                // set the color of the selected radio button text to green to indicate correct choice
                ans.setTextColor(Color.GREEN);
                // 1.5 seconds later ...
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // set the color of the text back to black, as each question remains on screen 1.5 seconds
                        // after the user has selected 'Next question'
                        ans.setTextColor(Color.BLACK);
                    }
                }, 1500);

            } else {
                // the user has incorrectly answered
                // set the color of the chosen radio button to red
                ans.setTextColor(Color.RED);
                //https://stackoverflow.com/questions/26314406/runnable-handler-not-executing-inside-fragment-could-not-start-a-runnable
                // 1.5 seconds later ...
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // set the color of the selected radio button back to black, as the view is recycled
                        ans.setTextColor(Color.BLACK);
                    }
                }, 1500);

                // then, to make the correct answer radio button show text in green,
                // iterate through each radio button in radio group
                for(int i = 0; i < radio_group.getChildCount(); i ++) {

                    cycle_radio_button =  (RadioButton)radio_group.getChildAt(i);

                    // if the current iteration radio button has text that equals the current Question's correct answer
                    if (cycle_radio_button.getText().equals(currentQuestion.getAnswer())) {
                        // set this current iteration radio button text to green
                        cycle_radio_button.setTextColor(Color.GREEN);
                        // 1.5 seconds later ...
                        Handler radio_handler = new Handler();
                        radio_handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // set the color back to black
                                cycle_radio_button.setTextColor(Color.BLACK);
                            }
                        }, 1500);
                        break;
                    }
                }
            }

            // this sets the view as long as the number of questions answered is less than 10
            if (numQuestionsAnswered < 10) {

                currentQuestion = setOfQuestions.get(numQuestionsAnswered);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    // delay of 1.5 seconds after button click to show next question
                    @Override
                    public void run() {
                        setView();
                    }
                }, 1500);


            } else {
                // else the user has finished the quiz
                SharedPreferences.Editor prefsEditor = sharedPreferences.edit();

                // store current score user has achieved into sharedPreferences
                prefsEditor.putInt("current_score", currentScore);
                prefsEditor.commit();

                // transition to the ResultFragment page after 1.5 seconds
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ResultFragment rf = new ResultFragment();
                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.quiz_container, rf);
                        ft.addToBackStack(null);
                        ft.commit();
                    }
                }, 1500);

            }
        }
    }



}

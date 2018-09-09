package edu.auburn.eng.csse.comp3710.spring2018.ArrayIndexOutOfBoundsException;

import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.RunnableScheduledFuture;

/**
 *
 * names:       Brandon Toups, Evan McCarthy
 * email:       bmt0015        esm0012
 * date:        2 May 2018
 * class:       COMP3710
 * team:        ArrayIndexOutOfBoundsException
 * package:     edu.auburn.eng.csse.comp3710.spring2018.ArrayIndexOutOfBoundsException;
 * file:        MainActivity.java
 *
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // default entry for String based shared preferences
    private final String emptyEntry = "N/A";

    // primary login screen show password button; want to login button
    Button showButton, loginButton;

    // primary login screen enter your name, enter your password, enter your email
    EditText nameEntry, passwordEntry, emailEntry;

    // for choosing avatar
    Spinner spinner;

    // icon presented
    ImageView loginIcon;

    // the text entered on the primary login screen
    TextView name_entered;

    // enter password on secondary login screen
    EditText enter_password_again;

    // for secondary login screen
    Button show_password_again, loginAgainButton;

    // MODE_PRIVATE: File creation mode: file can only be accessed by the calling application
    SharedPreferences sharedPreferences;

    // array for populating the spinner
    String[] avatarArray = {"Choose Avatar", "Jedi", "Vader"};

    // sharedPreferences localized variables
    String nameStore;
    String passwordStore;
    String emailStore;
    String avatarStore;
    int scoreStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // getSharedPreferences(String name, int mode)
        sharedPreferences = getSharedPreferences("User information", Context.MODE_PRIVATE);

        // name stored in sharedPreferences
        nameStore = sharedPreferences.getString("user_name", emptyEntry);
        // password
        passwordStore = sharedPreferences.getString("user_password", emptyEntry);
        // email
        emailStore = sharedPreferences.getString("user_email", emptyEntry);
        // avatar
        avatarStore = sharedPreferences.getString("user_avatar", emptyEntry);
        // score
        scoreStore = sharedPreferences.getInt("user_score", 0);

        // if the user has not entered a name in the primary login screen
        if (nameStore.equals(emptyEntry)) {
            // name, email, avatar, password screen
            setContentView(R.layout.activity_main);

            // text fields resources
            passwordEntry = (EditText) findViewById(R.id.passwordEntry);
            emailEntry = (EditText) findViewById(R.id.emailEntry);
            nameEntry = (EditText) findViewById(R.id.nameEntry);

            // avatar spinner
            spinner = (Spinner) findViewById(R.id.spinner);

            // spinner adapter for populating with 'Jedi,' 'Vader'.
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner, avatarArray);
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            // user clicks to login
            loginButton = (Button) findViewById(R.id.loginButton);
            loginButton.setOnClickListener(this);

            // user wants to view his hidden password
            showButton = (Button) findViewById(R.id.showButton);
            showButton.setOnClickListener(this);

        // else the user has already entered a nme in the primary login,
        // meaning the application has stored a name in the shared preferences for name
        } else {
            // secondary login screen
            setContentView(R.layout.remember_user);

            // secondary login various fields
            enter_password_again = (EditText) findViewById(R.id.enter_password_again);
            name_entered = (TextView) findViewById(R.id.name_entered);

            // "Welcome back __name__"
            name_entered.setText(nameStore);

            // secondary login screen show password button
            show_password_again = (Button) findViewById(R.id.show_password_again);
            show_password_again.setOnClickListener(this);

            // on the secondary login screen, the loginIcon will be populated by whichever avatar the user has selected
            loginIcon = (ImageView) findViewById(R.id.loginIcon);

            // setting loginIcon to the specific image
            if (avatarStore.equals("Jedi")) {
                loginIcon.setImageResource(R.drawable.jedi);
            } else if (avatarStore.equals("Vader")) {
                loginIcon.setImageResource(R.drawable.vader);
            }

            // this is merely in place for testing purposes, so the user does not forget the password he has entered.
            Toast.makeText(MainActivity.this,"Reminder. Password = " + sharedPreferences.getString("user_password",emptyEntry),Toast.LENGTH_LONG).show();

            // secondary login screen log in button
            loginAgainButton = (Button) findViewById(R.id.loginAgainButton);
            loginAgainButton.setOnClickListener(this);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {

            case R.id.loginButton:
                if (nameStore.equals(emptyEntry) || passwordStore.equals(emptyEntry) || emailStore.equals(emailStore) || avatarStore.equals(emptyEntry)) {
                    // initialization to store in sharedPreferences files
                    String savedName = nameEntry.getText().toString();
                    String savedEmail = emailEntry.getText().toString();
                    String savedPassword = passwordEntry.getText().toString();
                    String savedAvatar = spinner.getSelectedItem().toString();

                    // if user has not entered all of the fields, do nothing, but remind them to enter.
                    if (savedName.equals("") || savedEmail.equals("") || savedPassword.equals("") || savedAvatar.equals("Choose Avatar")) {
                        try {
                            Toast.makeText(MainActivity.this, "You have not entered all your information", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) { }
                    // else store all the entered fields into the sharedPreferences
                    } else {
                        // user has entered all preferences. We can save these preferences in app now.
                        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
                        prefsEditor.putString("user_name", savedName);
                        prefsEditor.putString("user_email", savedEmail);
                        prefsEditor.putString("user_password", savedPassword);
                        prefsEditor.putString("user_avatar", savedAvatar);
                        prefsEditor.putInt("high_score", 0);
                        prefsEditor.putInt("current_score", 0);
                        prefsEditor.commit();
                        // and start the Navigation class
                        Intent intent = new Intent(MainActivity.this, Navigation.class);
                        startActivity(intent);
                        finish();

                    }
                }
                break;

            case R.id.loginAgainButton:
                // if the password entered matches that of the sharedPreferences entered at initial login
                if (sharedPreferences.getString("user_password", emptyEntry).equals(enter_password_again.getText().toString())) {
                    // start Navigation class
                    Intent intent = new Intent(MainActivity.this, Navigation.class);
                    startActivity(intent);
                    finish();

                // else remind the user he or she has entered the wrong password
                } else {
                    Toast.makeText(MainActivity.this, "You have entered the wrong password", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.showButton:
                showPassword();
                break;

            case R.id.show_password_again:
                showPasswordAgain();
                break;

            default:
                break;
        }
    }

    // https://stackoverflow.com/a/5231435
    // why does this take two presses for this to begin working properly
    void showPassword() {
        if (showButton.getText().toString() == "SHOW") {
            passwordEntry.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            showButton.setText("HIDE");
        } else {
            passwordEntry.setTransformationMethod(PasswordTransformationMethod.getInstance());
            showButton.setText("SHOW");
        }
    }
    // secondary login screen 'SHOW' password
    void showPasswordAgain() {
        if (show_password_again.getText().toString() == "SHOW") {
            enter_password_again.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            show_password_again.setText("HIDE");
        } else {
            enter_password_again.setTransformationMethod(PasswordTransformationMethod.getInstance());
            show_password_again.setText("SHOW");
        }
    }


}
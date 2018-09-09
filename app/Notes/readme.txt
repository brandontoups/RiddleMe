names: 	Brandon Toups, Evan McCarthy
emails: bmt0015	       esm0012
team: 	ArrayIndexOutOfBoundsException
class:	COMP3710-001
date: 	3 May 2018

Name of the app
-   Riddle Me (branded as ‘riddle me’)

1-5 line description of what the app does:
Our application quizzes users on the intersection of their Internet History and boy-band knowledge. Splash screen denotes names, name of app,
university, and class. The app opens to a login screen with option to enter name, email, avatar (Jedi, Vader, or Umphress) and a password.
User is taken to a new page. Based on avatar selection, different music will play. At the top of the Navigation screen, the user
will see his or her correct avatar, name, and email. On this page, user can choose between playing the quiz or changing settings. Clicking
quiz begins the quiz, with 10 multiple choice questions. The user takes the quiz, and is told his or her score at the end. The high score is also
shown. High score is updated if user achieves it, and animation show.

Justification for locking orientation on a few layouts:
-   The multiple choice portion of the app has very text heavy questions that would not fit as well on the screen if orientation changed.
-   There are too many settings for them to fit on the screen effectively if the screen orientation is changed.

Special instructions needed to build the app:
-   Emulator/phone running Android 7.1.1; API Level 25

Known bugs:
-   Bug bug = (Feature) findViewById(R.raw.music); The music won’t stop playing until onDestroy is called on the Navigation class. We
    have implemented back button functionality to allow for proper navigation through the app, with Navigation screen back button killing the app
    (as going back to a login screen seems arbitrary. The physical home button will not kill the music, but killing the app in app
    view hierarchy will stop the music.
-   Ignore ArrayIndexOutOfBoundsException in Logcat. This is the name of our group.
-   On the login screen, the ‘SHOW’ button does not work until it is clicked twice. We believe we have implemented it exactly as
    it should be. After the second button press, it works perfectly.

Interesting implementation details (coolness factor):
-   Music playing is dependent on avatar decision. Picking ‘Jedi’ will play the Star Wars theme song. Picking ‘Umphress’ or ‘Vader’ will
    play the Imperial March throughout the app.
-   Music is implemented as a Service with startServices(Intent) to allow persistence and continuous playback of music across activities and fragments.
-   Music is able to be muted/unmuted in Settings.
-   When a correct answer is chosen, text turns green. If incorrect answer chosen, incorrect answer text turns red, and the actual correct
    answer text turns green.
-   We tried to adhere to Material Design as best as possible via color themes and stylistic choices inside activities and fragments, for
    the overall theme design, for the radio buttons in the quiz, and for the splash screen.
-   Quiz questions and answers stored in database and accessed via sqlite commands.
-   Shared preferences of name, email, password, and avatar decision, as well as current score and overall high score.
-   Name, email, avatar can be changed from settings screen.
-   High score can be reset from settings screen.
-   When the app is quit, the shared preferences allows a secondary login screen that shows the user's chosen avatar, and welcomes user
    back by name. User must enter original, correct password or is not granted access.
-   High score achievement shows a fun animation.

Documentation of miscellaneous sources:
-   https://www.freelancer.ie/contest/Android-material-design-login-page-293148-byentry-7036138.html?utm_expid=294858-566.5XyInbHKSlyskM-GmmPexA.0&utm_referrer=https:%2F%2Fwww.google.com%2F
-   http://www.java2s.com/Code/Android/Database/extendsSQLiteOpenHelper.htm
-   https://developer.android.com/reference/android/database/sqlite/SQLiteOpenHelper
-   https://www.khanacademy.org/computer-programming/sql-create-table-with-a-primary-key/5189331400654848
-   https://www.codota.com/android/classes/android.content.ContentValues
-   https://www.materialpalette.com/blue/deep-orange
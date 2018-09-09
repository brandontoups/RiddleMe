package edu.auburn.eng.csse.comp3710.spring2018.ArrayIndexOutOfBoundsException;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * names:       Brandon Toups, Evan McCarthy
 * email:       bmt0015        esm0012
 * date:        2 May 2018
 * class:       COMP3710
 * team:        ArrayIndexOutOfBoundsException
 * package:     edu.auburn.eng.csse.comp3710.spring2018.ArrayIndexOutOfBoundsException;
 * file:        Database.java
 *
 */

public class Database extends SQLiteOpenHelper {

    // database version
    private static final int DB_VERSION = 2;

    // name of the database
    private static final String DB_NAME = "riddle.db";

    // name of the table for storing questions
    private static final String DB_TABLE = "questionTable";

    // id of the question
    private static final String KEY_ID = "id";

    // the question string
    private static final String KEY_QUESTION = "question";

    // choice a string
    private static final String KEY_A = "a";

    // choice b string
    private static final String KEY_B = "b";

    // choice c string
    private static final String KEY_C = "c";

    // choice d string
    private static final String KEY_D = "d";

    // answer string
    private static final String KEY_ANSWER = "answer";

    // SQLiteDatabase object instantiation
    private SQLiteDatabase database;

    // current row
    private int row = 0;

    // Database ctor
    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        database = db;

        /**
         *                      riddle.db
         *
         *        0       1         2     3   4   5   6
         *      +----+----------+--------+---+---+---+---+
         *      | id | question | answer | a | b | c | d |
         *      +----+----------+--------+---+---+---+---+
         *      |    |          |        |   |   |   |   |
         *      +----+----------+--------+---+---+---+---+
         *      |    |          |        |   |   |   |   |
         *      +----+----------+--------+---+---+---+---+
         *      |    |          |        |   |   |   |   |
         *      +----+----------+--------+---+---+---+---+
         *
         */
        String query = String.format("CREATE TABLE IF NOT EXISTS " +
                "%s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "%s TEXT, " +
                "%s TEXT, " +
                "%s TEXT, " +
                "%s TEXT, " +
                "%s TEXT, " +
                "%s TEXT )",
                DB_TABLE, KEY_ID, KEY_QUESTION, KEY_ANSWER, KEY_A, KEY_B, KEY_C, KEY_D);
        // execute query
        db.execSQL(query);
        addQuestions();
    }

    // (question,answer,a,b,c,d)
    private void addQuestions() {
        // 30 question constructors. New lines for consistency, clairty, and ease of input
        Question question1 = new Question(
                "What is auburn's mascot?",
                "Aubie",
                "Aubie",
                "Tigers",
                "Fill",
                "Grrr");
        Question question2 = new Question(
                "What is the 7th planet from the sun?",
                "Uranus",
                "Mercury",
                "Venus",
                "Earth",
                "Uranus");
        Question question3 = new Question(
                "What is the world's longest river?",
                "Amazon",
                "Nile",
                "Amazon",
                "Mississippi",
                "Tigris");
        Question question4 = new Question(
                "What country is Prague in?",
                "Czech Republic",
                "Turkey",
                "Lithuania",
                "Czech Republic",
                "Slovakia");
        Question question5 = new Question(
                "What color jersey is worn by the winner of each stage in the Tour de France?",
                "Yellow",
                "Red",
                "Blue",
                "Yellow",
                "White");

        Question question6 = new Question(
                "Which fast-disappearing island nation receives 10% of its income from royalties from the use of its national internet domain suffix?",
                "Tuvalu (.tv)",
                "Commercial Island (.com)",
                "Afghanistan (.af)",
                "Tuvalu (.tv)",
                "Thailand (.th)");
        Question question7 = new Question(
                "What is the full name of the Australian boyband 5SOS?",
                "5 Seconds of Summer",
                "5 Sons of Sauron",
                "5 Seconds of Summer",
                "5 Stars or Stripes",
                "5SOS");
        // lol https://www.proprofs.com/quiz-school/story.php?title=best-10-justin-bieber-quiz-questions
        Question question8 = new Question(
                "What is Justin Bieber's middle name?",
                "Drew",
                "Jake",
                "Drew",
                "Luke",
                "Drake");
        Question question9 = new Question(
                "What is Justin Bieber's favorite color?",
                "Purple",
                "Blue",
                "Red",
                "Black",
                "Purple");
        Question question10 = new Question(
                "In 1969, two computers connected for the first documented time, but crashed when the letters \"LO\" were typed in. What word was trying to be transmitted?",
                "Login",
                "LOL",
                "Login",
                "Love you",
                "Lord of the Rings");
        Question question11 = new Question(
                "Which pioneer in computing developed the first website: info.cern.ch?",
                "Tim Berners-Lee",
                "Charles Babbage",
                "Konrad Zuse",
                "Tim Berners-Lee",
                "Steve Wozniak");
        Question question12 = new Question(
                "The first webcam was set up to monitor what?",
                "Coffee pot",
                "Weather outside",
                "Coffee pot",
                "Security camera for a building",
                "Take selfies");
        Question question13 = new Question(
                "Ray Tomlinson, the first person to send an e-mail, also came up with what innovation?",
                ".com in URL names",
                "First webpage",
                ".com in URL names",
                "Earliest programming language",
                "Built the first working computer");
        Question question14 = new Question(
                "What was the first official programming language?",
                "Plankalkül",
                "Plankalkül",
                "COBOL",
                "FORTRAN",
                "LISP");
        Question question15 = new Question(
                "The first gigabyte hard drive weighed how much?",
                "550 pounds",
                "20 pounds",
                "3 tons",
                "200 pounds",
                "550 pounds");
        Question question16 = new Question(
                "The White House came online with .gov in what year?",
                "1993",
                "1980",
                "1993",
                "1999",
                "1976");
        Question question17 = new Question(
                "The anonymous Satoshi Nakamoto invented what system",
                "Bitcoin",
                "WWW",
                "Transmission Control Protocol",
                "Bitcoin",
                "IPv4");
        Question question18 = new Question(
                "Which is a test of a machine's ability to exhibit intelligent behavior equivalent to, or indistinguishable from, that of a human?",
                "Turing test",
                "The imitation game",
                "CSGO",
                "Turing test",
                "AlphaGo");
        Question question19 = new Question(
                "What percentage of the world's currency exists on computers (non-physical)?",
                "92%",
                "17%",
                "1.5%",
                "92%",
                "65%");
        Question question20 = new Question(
                "The U.S. nuclear missle controls used what password for a period of 8 years?",
                "00000000",
                "00000000",
                "password",
                "12345678",
                "skater_boy1996");
        Question question21 = new Question(
                "The first computer bug was what?",
                "A dead moth",
                "Y2K bug",
                "WannaCry",
                "Gangnam Style having too many views",
                "A dead moth");
        Question question22 = new Question(
                "The first computer mouse was made of what material?",
                "Wood",
                "Wood",
                "Plastic",
                "Aluminum",
                "Silicone gel");
        Question question23 = new Question(
                "The main algorithm that allowed Google to be as successful as it is today is: ?",
                "PageRank",
                "EdgeRank",
                "PageRank",
                "Cook-Levin reduction",
                "Traveling salesman");
        Question question24 = new Question(
                "The most up to date Android version is what?",
                "Android P",
                "Oreo",
                "Android P",
                "Nougat",
                "Marshmallow");
        Question question25 = new Question(
                "IPv6 uses how many bits for addresses?",
                "128",
                "256",
                "128",
                "64",
                "1024");
        Question question26 = new Question(
                "How many searches does Google handle every year?",
                "2 trillion",
                "120 billion",
                "16 billion",
                "4",
                "2 trillion");
        Question question27 = new Question(
                "NSYNC's members are?",
                "Justin Timberlake, JC Chasez, Chris Kirkpatrick, Joey Fatone, and Lance Bass",
                "Map, Reduce, Filter, Each, Detect",
                "AJ McLean, Howie D., Nick Carter, Kevin Richardson and Brian Littrell",
                "Niall Horan. Liam Payne. Harry Styles. Louis Tomlinson",
                "Justin Timberlake, JC Chasez, Chris Kirkpatrick, Joey Fatone, and Lance Bass");
        Question question28 = new Question(
                "How many lines of code did Windows 98 OS contain?",
                "18 million",
                "10 million",
                "400,000",
                "125,000",
                "18 million");
        Question question29 = new Question(
                "Linux file system supports single files up to: ?",
                "16 TB",
                "16 TB",
                "500 GB",
                "4 MB",
                "No limit");
        Question question30 = new Question(
                "How many possible IPv6 addresses are there?",
                "340 undecillion",
                "340 undecillion",
                "340 quintillion",
                "950 billion",
                "1.024 million");

        // add the newly constructed questions to the database
        this.populateDatabase(question1);
        this.populateDatabase(question2);
        this.populateDatabase(question3);
        this.populateDatabase(question4);
        this.populateDatabase(question5);

        this.populateDatabase(question6);
        this.populateDatabase(question7);
        this.populateDatabase(question8);
        this.populateDatabase(question9);
        this.populateDatabase(question10);

        this.populateDatabase(question11);
        this.populateDatabase(question12);
        this.populateDatabase(question13);
        this.populateDatabase(question14);
        this.populateDatabase(question15);

        this.populateDatabase(question16);
        this.populateDatabase(question17);
        this.populateDatabase(question18);
        this.populateDatabase(question19);
        this.populateDatabase(question20);

        this.populateDatabase(question21);
        this.populateDatabase(question22);
        this.populateDatabase(question23);
        this.populateDatabase(question24);
        this.populateDatabase(question25);

        this.populateDatabase(question26);
        this.populateDatabase(question27);
        this.populateDatabase(question28);
        this.populateDatabase(question29);
        this.populateDatabase(question30);

    }

    // (question,answer,a,b,c,d)
    public void populateDatabase(Question q) {
        ContentValues values = new ContentValues();

        values.put(KEY_QUESTION, q.getQuestion());
        values.put(KEY_ANSWER, q.getAnswer());
        values.put(KEY_A, q.getAnsA());
        values.put(KEY_B, q.getAnsB());
        values.put(KEY_C, q.getAnsC());
        values.put(KEY_D, q.getAnsD());

        database.insert(DB_TABLE, null, values);
    }

    public List<Question> retrieveQuestions() {

        List<Question> questionList = new ArrayList<Question>();

        database = this.getReadableDatabase();

        String select = "SELECT * FROM " + DB_TABLE;

        Cursor cursor = database.rawQuery(select,null);

        // current row
        row = cursor.getCount();

        if (cursor.moveToFirst()) {
            do {
                Question ques = new Question();
                ques.setQuesId(cursor.getInt(0));
                ques.setQuestion(cursor.getString(1));
                ques.setAnswer(cursor.getString(2));
                ques.setAnsA(cursor.getString(3));
                ques.setAnsB(cursor.getString(4));
                ques.setAnsC(cursor.getString(5));
                ques.setAnsD(cursor.getString(6));

                questionList.add(ques);
            } while (cursor.moveToNext());
        }

        // list of all questions
        return questionList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Robert'); DROP TABLE students;--
        db.execSQL("DROP TABLE IF EXISTS "+ DB_TABLE);
        onCreate(db);
    }

    public int getRowCount(){
        return row;
    }


}

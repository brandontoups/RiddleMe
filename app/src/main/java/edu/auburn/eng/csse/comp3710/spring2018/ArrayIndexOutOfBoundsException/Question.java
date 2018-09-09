package edu.auburn.eng.csse.comp3710.spring2018.ArrayIndexOutOfBoundsException;

/**
 *
 * names:       Brandon Toups, Evan McCarthy
 * email:       bmt0015        esm0012
 * date:        2 May 2018
 * class:       COMP3710
 * team:        ArrayIndexOutOfBoundsException
 * package:     edu.auburn.eng.csse.comp3710.spring2018.ArrayIndexOutOfBoundsException;
 * file:        Question.java
 *
 */

public class Question {

    // question text
    private String question;

    // answer choice a text
    private String a;

    // answer choice b text
    private String b;

    // answer choice c text
    private String c;

    // answer choice d text
    private String d;

    // answer text
    private String answer;

    // question id
    private int quesId;

    // explicitly-defined default ctor
    public Question() {
        quesId = 0;
        question = "";
        a = "";
        b = "";
        c = "";
        d= "";
        answer = "";
    }

    // overloaded ctor -- Question(question, answer, a, b, c, d)
    public Question(String question, String answer, String a, String b, String c, String d) {
        this.question = question;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.answer = answer;
    }

    // getters

    public int getQuesId() {
        return quesId;
    }

    public String getAnswer() {
        return answer;
    }

    public String getAnsA() {
        return a;
    }

    public String getAnsB() {
        return b;
    }

    public String getAnsC() {
        return c;
    }

    public String getAnsD() {
        return d;
    }

    public String getQuestion() {
        return question;
    }

    // setters

    public void setQuesId(int newId) {
        quesId = newId;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnsA(String a) {
        this.a = a;
    }

    public void setAnsB(String b) {
        this.b = b;
    }

    public void setAnsC(String c) {
        this.c = c;
    }

    public void setAnsD(String d) {
        this.d = d;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}

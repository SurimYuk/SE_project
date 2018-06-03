package com.example.sj971.score;

/**
 * Created by sj971 on 2018-05-29.
 */

public class ScoreItem {

    String subject;
    String score;

    public ScoreItem(){
    }

    public ScoreItem(String subject, String score) {
        this.subject = subject;
        this.score = score;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}


package com.example.sj971.score;

/**
 * Created by sj971 on 2018-05-29.
 */

public class SubjectItem {

    String ID; //학생 아이디 -> 학번
    String number; //.학수번호
    String subject; //.과목 이름

    public SubjectItem(){

    }

    public SubjectItem(String number, String subject) {
        //this.ID=id;
        this.number = number;
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String score) {
        this.number = score;
    }

    public String getID() {
        return ID;
    }

    public void setID(String id) {
        this.ID = id;
    }
}

package com.example.sj971.score;

/**
 * Created by sj971 on 2018-05-30.
 */

public class ManageItem {

    String number;
    String subject;
    String professor;

    public ManageItem(String number, String subject, String professor) {
        this.number = number;
        this.subject = subject;
        this.professor=professor;
    }

    public String getManage_Subject() {
        return subject;
    }

    public void setManage_Subject(String subject) {
        this.subject = subject;
    }

    public String getManage_Number() {
        return number;
    }

    public void setManage_Number(String number) {
        this.number = number;
    }

    public String getManage_Professor() {
        return professor;
    }

    public void setManage_Professor(String professor) {
        this.professor = professor;
    }
}

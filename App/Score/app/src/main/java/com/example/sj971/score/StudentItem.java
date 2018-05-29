package com.example.sj971.score;

import android.content.Context;

/**
 * Created by sj971 on 2018-05-29.
 */

public class StudentItem{

    String student_name; //.학생 이름
    String student_score;
    String[] student_grade;

    public StudentItem(String student_name ,String student_score) {
        this.student_name = student_name;
        this.student_score=student_score;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String name) {
        this.student_name = name;
    }

    public String getStudent_score() {
        return student_score;
    }

    public void setStudent_score(String score) {
        this.student_score = score;
    }

    public void setGrade(String[] grade){
        this.student_grade=grade;
    }

    public String[] getStudent_grade(){
        return student_grade;
    }
}
